package com.propertyservice.propertyservice.repository.company;

import com.propertyservice.propertyservice.domain.client.QClient;
import com.propertyservice.propertyservice.domain.company.QCompany;
import com.propertyservice.propertyservice.domain.company.QDepartment;
import com.propertyservice.propertyservice.domain.manager.QManager;
import com.propertyservice.propertyservice.domain.property.QProperty;
import com.propertyservice.propertyservice.domain.revenue.QRevenueLedger;
import com.propertyservice.propertyservice.dto.manager.CustomUserDetail;
import com.propertyservice.propertyservice.dto.manager.ManagerInfoDto;
import com.propertyservice.propertyservice.dto.manager.QManagerInfoDto;
import com.propertyservice.propertyservice.service.CommonService;
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

    private final CommonService commonService;
    private final QManager manager = QManager.manager;
    private final QCompany company = QCompany.company;
    private final QDepartment department = QDepartment.department;
    private final QRevenueLedger revenueLedger = QRevenueLedger.revenueLedger;
    private final QProperty property = QProperty.property;
    private final QClient client = QClient.client;
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
                .innerJoin(company).on(manager.company.eq(company))
                .innerJoin(department).on(manager.department.eq(department))
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
                                revenueLedger.commission.sum().coalesce(BigDecimal.ZERO)
                        )
                )
                .from(manager)
                //로그인한 회사 조건 추가.
                .innerJoin(company).on(manager.company.eq(company).and(company.eq(commonService.getCustomUserDetailBySecurityContextHolder().getCompany())))
                .innerJoin(revenueLedger).on(manager.eq(revenueLedger.manager))
                .where(manager.department.departmentId.eq(departmentId)
                        .and(formattedDate.eq(currentDate))
                )
                .groupBy(revenueLedger.manager)
                .fetch();
    }

    /**
     * 매니저 한명 총 매출 합계.
     * @param managerId
     * @return
     */
    @Override
    public BigDecimal managerTotalRevenue(Long managerId) {
        return  queryFactory
                .select(
                        revenueLedger.commission.sum().coalesce(BigDecimal.ZERO)
                )
                .from(manager)
                //로그인한 회사 조건 추가.
                .innerJoin(company).on(company.eq(manager.company).and(company.eq(commonService.getCustomUserDetailBySecurityContextHolder().getCompany())))
                .innerJoin(revenueLedger).on(manager.eq(revenueLedger.manager))
                .groupBy(manager.managerId)
                .having(manager.managerId.eq(managerId))
                .fetchOne();
    }

    @Override
    public BigDecimal managerTotalRevenueMonth(Long managerId) {
        // 현재 년-월
        StringTemplate currentDate = formattedDate(LocalDateTime.now(), "%Y-%m");

        // ex) 2024-07
        StringTemplate formattedDate = formattedDate(revenueLedger.createdDate, "%Y-%m");
        return  queryFactory
                .select(
                        revenueLedger.commission.sum().coalesce(BigDecimal.ZERO)
                )
                .from(manager)
                //로그인한 회사 조건 추가.
                .innerJoin(company).on(manager.company.eq(company).and(company.eq(commonService.getCustomUserDetailBySecurityContextHolder().getCompany())))
                .innerJoin(revenueLedger).on(manager.eq(revenueLedger.manager))
                .where(formattedDate.eq(currentDate))
                .groupBy(manager.managerId, formattedDate)
                .having(manager.managerId.eq(managerId))
                .fetchOne();
    }

    @Override
    public BigDecimal managerPicProperty(Long managerId) {
        // 담당 매물 수.
        return BigDecimal.valueOf(queryFactory
                .select(
                        property
                )
                .from(manager)
                //로그인한 회사 조건 추가.
                .innerJoin(company).on(manager.company.eq(company).and(company.eq(commonService.getCustomUserDetailBySecurityContextHolder().getCompany())))
                .innerJoin(property).on(manager.managerId.eq(property.picManagerId))
                .where(manager.managerId.eq(managerId))
                .fetch().size());
    }

    @Override
    public BigDecimal managerPicClient(Long managerId) {
        // 담당 고객 수
        return  BigDecimal.valueOf(queryFactory
                .select(
                        client
                )
                .from(manager)
                //로그인한 회사 조건 추가.
                .innerJoin(company).on(manager.company.eq(company).and(company.eq(commonService.getCustomUserDetailBySecurityContextHolder().getCompany())))
                .innerJoin(client).on(manager.managerId.eq(client.managerId))
                .where(manager.managerId.eq(managerId))
                .fetch().size());
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
