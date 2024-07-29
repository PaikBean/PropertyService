package com.propertyservice.propertyservice.repository.company;


import com.propertyservice.propertyservice.domain.company.Department;
import com.propertyservice.propertyservice.domain.company.QCompany;
import com.propertyservice.propertyservice.domain.company.QDepartment;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.domain.revenue.QRevenueLedger;
import com.propertyservice.propertyservice.dto.company.*;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    private final QManager manager = QManager.manager;
    private final QCompany company = QCompany.company;
    private final QDepartment department = QDepartment.department;
    private final QRevenueLedger revenueLedger = QRevenueLedger.revenueLedger;

    @Override
    public List<DepartmentDto> searchDepartmentListByCompanyCode(String companyCode) {
        return queryFactory
                .select(
                        new QDepartmentDto(
                                department.departmentId,
                                department.departmentName
                        )
                )
                .from(department)
                .join(company).on(department.company.eq(company))
                .where(
                        company.companyCode.eq(companyCode)
                )
                .fetch();
    }

    @Override
    public List<DepartmentInfoDto> searchDepartmentList(Long companyId) {
        return queryFactory
                .select(
                    new QDepartmentInfoDto(
                            department.departmentId,
                            department.departmentName,
                            department.departmentCode,
                            department.departmentPresidentName.managerName
                    )
                ).from(department)
                .leftJoin(company).on(department.company.eq(company))
                .where(
                        company.companyId.eq(companyId)
                )
                .fetch();
//        return null;
    }

    @Override
    public List<DepartmentInfoDto> searchDepartmentInfo(Long departmentId) {
        return queryFactory
                .select(
                        new QDepartmentInfoDto(
                                department.departmentId,
                                department.departmentName,
                                department.departmentCode,
                                department.departmentPresidentName.managerId,
                                revenueLedger.commission.sum()
                        )
                )
                .from(department)
                .innerJoin(revenueLedger)
                .on(department.departmentCode.eq(revenueLedger.departmentCode))
                .where(department.departmentId.eq(departmentId))
                .fetch();
//        return null;
    }

    @Override
    public List<BigDecimal> searchDepartmentTotalRevenue(DepartmentTotalRevenueCondition departmentTotalRevenueCondition) {
        StringTemplate formattedDate = formattedDate(revenueLedger.createdDate, "%Y%m%D");

        return  queryFactory
                .select(
                        revenueLedger.commission.sum()
                )
                .from(manager)
                .innerJoin(revenueLedger).on(manager.eq(revenueLedger.managerId))
                .where(manager.department.departmentId.eq(departmentTotalRevenueCondition.getDepartmentId())
                        .and(
                                formattedDate.between(departmentTotalRevenueCondition.getStartDate(), departmentTotalRevenueCondition.getEndDate())
                        )
                )
                .fetch();
//        return null;
    }
    public StringTemplate formattedDate(DateTimePath<LocalDateTime> date, String format){
        return  Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , date
                , ConstantImpl.create(format));
    }
    public StringTemplate formattedDate(LocalDateTime date, String format){
        return  Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , date
                , ConstantImpl.create(format));
    }
}
