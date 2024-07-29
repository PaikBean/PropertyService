package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.company.QCompany;
import com.propertyservice.propertyservice.domain.company.QDepartment;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.domain.revenue.QRevenueLedger;
import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import com.propertyservice.propertyservice.dto.manager.QManagerInfoDto;
import com.querydsl.core.Tuple;
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
public class ManagerRepositoryImpl implements ManagerRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final QManager manager = QManager.manager;
    private final QCompany company = QCompany.company;
    private final QDepartment department = QDepartment.department;
    private final QRevenueLedger revenueLedger = QRevenueLedger.revenueLedger;
    @Override
    public List<ManagerInfoDto> searchManagerInfoListByCompanyId(Long companyId) {
        return queryFactory
                .select(
                    new QManagerInfoDto(
                            manager.managerId,
                            department.departmentName,
                            manager.managerName,
                            manager.managerRank,
                            manager.managerPosition
                    )
                )
                .from(manager)
                .leftJoin(company).on(manager.company.eq(company))
                .leftJoin(department).on(manager.department.eq(department))
                .where( manager.company.companyId.eq(companyId))
                .fetch();

    }

    @Override
    public List<ManagerInfoDto> searchManagerInfoListByDepartmentId(Long departmentId) {
        // 현재 년-월
        StringTemplate currentDate = formattedDate(LocalDateTime.now(), "%Y-%m");

        // ex) 2024-07
        StringTemplate formattedDate = formattedDate(revenueLedger.createdDate, "%Y-%m");

        return queryFactory
                .select(
                        new QManagerInfoDto(
                                manager.managerId,
                                manager.managerPosition,
                                manager.managerName,
                                manager.managerRank,
                                manager.managerPosition,
                                revenueLedger.commission.sum()
                        )
                )
                .from(manager)
                .leftJoin(revenueLedger).on(manager.eq(revenueLedger.managerId))
                .where(manager.department.departmentId.eq(departmentId)
                        .and(formattedDate.eq(currentDate))
                )
                .groupBy(revenueLedger.managerId)
                .fetch();
    }

    @Override
    public List<BigDecimal> managerTotalRevenue(Long managerId) {
         return  queryFactory
                .select(
                        revenueLedger.commission.sum()
                )
                .from(manager)
                .innerJoin(revenueLedger).on(manager.eq(revenueLedger.managerId))
                .groupBy(manager.managerId)
                .having(manager.managerId.eq(managerId))
                .fetch();
    }

    @Override
    public List<BigDecimal> managerTotalRevenueMonth(Long managerId) {
        // 현재 년-월
        StringTemplate currentDate = formattedDate(LocalDateTime.now(), "%Y-%m");

        // ex) 2024-07
        StringTemplate formattedDate = formattedDate(revenueLedger.createdDate, "%Y-%m");
        return  queryFactory
                .select(
                        revenueLedger.commission.sum()
                )
                .from(manager)
                .innerJoin(revenueLedger).on(manager.eq(revenueLedger.managerId))
                .where(formattedDate.eq(currentDate))
                .groupBy(manager.managerId, formattedDate)
                .having(manager.managerId.eq(managerId))
                .fetch();
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
