package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.company.QCompany;
import com.propertyservice.propertyservice.domain.company.QDepartment;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import com.propertyservice.propertyservice.dto.manager.QManagerInfoDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ManagerRepositoryImpl implements ManagerRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final QManager manager = QManager.manager;
    private final QCompany company = QCompany.company;
    private final QDepartment department = QDepartment.department;
    @Override
    public List<ManagerInfoDto> searchManagerInfoList(Long companyId) {
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
}
