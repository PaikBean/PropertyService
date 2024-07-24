package com.propertyservice.propertyservice.repository.company;


import com.propertyservice.propertyservice.domain.company.Department;
import com.propertyservice.propertyservice.domain.company.QCompany;
import com.propertyservice.propertyservice.domain.company.QDepartment;
import com.propertyservice.propertyservice.dto.company.DepartmentDto;
import com.propertyservice.propertyservice.dto.company.DepartmentInfoDto;
import com.propertyservice.propertyservice.dto.company.QDepartmentDto;
import com.propertyservice.propertyservice.dto.company.QDepartmentInfoDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    private final QCompany company = QCompany.company;
    private final QDepartment department = QDepartment.department;

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
}
