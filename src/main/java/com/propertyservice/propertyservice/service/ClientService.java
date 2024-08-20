package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.client.Client;
import com.propertyservice.propertyservice.domain.client.ClientRemark;
import com.propertyservice.propertyservice.domain.client.InflowType;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.property.Property;
import com.propertyservice.propertyservice.domain.property.ShowingProperty;
import com.propertyservice.propertyservice.dto.client.*;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;
import com.propertyservice.propertyservice.repository.client.*;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import com.propertyservice.propertyservice.repository.property.PropertyRepository;
import com.propertyservice.propertyservice.utils.SummaryPrice;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientRemarkRepository clientRemarkRepository;
    private final ShowingPropertyRepository showingPropertyRepository;
    private final PropertyRepository propertyRepository;
    private final ManagerRepository managerRepository;
    private final CompanyRepository companyRepository;

    private final EntityExceptionService entityExceptionService;
    private final ScheduleService scheduleService;
    private final CommonService commonService;

    /**
     * 유입 경로 목록 조회.
     */
    public List<InflowTypeDto> searchInflowTypeList() {
        return Arrays.stream(InflowType.values())
                .map(inflowType -> new InflowTypeDto(inflowType.name(), inflowType.getLabel()))
                .collect(Collectors.toList());
    }

    /**
     * 보여줄 매물 목록 조회.
     */
    public List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
        List<ShowingPropertyCandidateDto> showingPropertyCandidateDtoList = propertyRepository.searchShowingPropertyCandidateList(showingPropertyCandidateCondition);
        for (ShowingPropertyCandidateDto showingPropertyCandidateDto : showingPropertyCandidateDtoList) {
            Property property = entityExceptionService.findEntityById(
                    () -> propertyRepository.findById(showingPropertyCandidateDto.getPropertyId()),
                    "매물 정보가 존재하지 않습니다. 관리자에게 문의하세요."
            );
            showingPropertyCandidateDto.setPrice(commonService.getSummaryPrice(property));
        }
        return showingPropertyCandidateDtoList;
    }

    /**
     * 고객 정보 단건 조회 - 고객 관리
     */
    public ClientInfoDto searchClientInfo(Long clientId){
        Client client = entityExceptionService.findEntityById(
                    () -> clientRepository.findById(clientId),
                    "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요."
        );
        return ClientInfoDto.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .inflowType(client.getInflowType().getLabel())
                .clientPhoneNumber(client.getClientPhoneNumber())
                .managerId(client.getManagerId())
                .clientRemarkList(searchClientRemarkList(clientId))
                .build();
    }

    /**
     * 고객 특이사항 추가.
     */
    public Long createClientRemark(ClientRemarkForm clientRemarkForm){
        // remark가 null인지 확인하는 작업 필요.
        if(clientRemarkForm.getRemark() != null){
            return  clientRemarkRepository.save(ClientRemark.builder()
                    .clientId(
                            entityExceptionService.findEntityById(
                                    () -> clientRepository.findById(clientRemarkForm.getClientId()),
                                    "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                    )
                    .remark(clientRemarkForm.getRemark())
                    .build()).getClientId();
        }
        return null;
    }

    /**
     * 고객 특이사항 삭제
     */
    @Transactional
    public void deleteClientRemark(Long clientRemarkId){
        clientRemarkRepository.delete(entityExceptionService.findEntityById(
                () -> clientRemarkRepository.findById(clientRemarkId),
                "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요."));
    }
    /**
     * 고객 정보 수정
     */
    @Transactional
    public Long updateClient(ClientForm clientForm){
        Client client = entityExceptionService.findEntityById(
                () -> clientRepository.findById(clientForm.getClientId()),
                "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요."
        );

        client.updateClient(clientForm);

        clientRepository.save(client);

        return client.getClientId();
    }

    public List<ClientDto.ClientListResponseDto> searchClientList(ClientCondition.clientListCondition clientListCondition){
        return clientRepository.searchClientList(clientListCondition.getClientName(), clientListCondition.getClientPhoneNumber());
    }

    /**
     * 고객 정보 단건 조회 - 고객 상세
     */
    public ClientDetailDto searchClientDetail(Long clientId){
        Client client = entityExceptionService.findEntityById(
                () -> clientRepository.findById(clientId),
                "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요."
        );

        //고객 일정
        List<ScheduleSummaryDto> scheduleSummaryList = scheduleService.searchScheduleListByClientId(client.getClientId());

        // 고객 매물
        List<ShowingPropertySummaryDto> showingPropertySummaryList = clientRepository.searchShowingPropertyList(client.getClientId());

        // 고객 특이사항.

        List<ClientRemarkDto> clientRemarkList = clientRepository.searchClientRemark(client.getClientId());

        return ClientDetailDto.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .inflowType(client.getInflowType().getLabel())
                .clientPhoneNumber(client.getClientPhoneNumber())
                .managerId(client.getManagerId())
                .scheduleList(scheduleSummaryList)
                .showingPropertyList(showingPropertySummaryList)
                .clientRemarkList(clientRemarkList)
                .build();
    }

    /**
     * 고객 특이사항 리스트.
     */
    public List<ClientRemarkDto> searchClientRemarkList(Long clientId){
        // 예외처리.
        return clientRepository.searchClientRemark(entityExceptionService.findEntityById(
                () -> clientRepository.findById(clientId),
                "고객 정보가 존재하지 않습니다. 관리자에게 문의하세요."
        ).getClientId());
    }



    public List<ClientDto.ClientListDto> searchClientList(Long companyId){
        // 예외처리.
       entityExceptionService.validateEntityExists(
                () -> companyRepository.findById(companyId),
                "회사가 존재하지 않습니다. 관리자에게 문의하세요."
        );
        return clientRepository.searchClientList(companyId);
    }




    // 고객 등록.
    public Long createClient(ClientForm clientForm){
        Client client =   clientRepository.save(Client.builder()
                .clientName(clientForm.getClientName())
                .clientPhoneNumber(clientForm.getClientPhoneNumber())
                .managerId(
                        entityExceptionService.findEntityById(
                                () -> managerRepository.findById(clientForm.getManagerId()),
                                "매니저 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                )
                .inflowType(InflowType.valueOf(clientForm.getInflowType()))
                .registrationManagerId(
                        entityExceptionService.findEntityById(
                                () -> managerRepository.findById(clientForm.getManagerId()),
                                "매니저 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                ) // 등록자 id는 담당자 id로 init
                .modifiedManagerId(
                        entityExceptionService.findEntityById(
                                () -> managerRepository.findById(clientForm.getManagerId()),
                                "매니저 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                ) // 수정자 id는 담당자 id로 init
                .build());

        // 특이사항이 있는 경우.
        if(clientForm.getRemark() != null){
            clientRemarkRepository.save(ClientRemark.builder()
                    .clientId(client.getClientId())
                    .remark(clientForm.getRemark())
                    .build());
        }

        // 매물리스트가 있는 경우.
        if(clientForm.getPropertyList()!= null) {
            for (Long propertyId : clientForm.getPropertyList()) {
                Property property = entityExceptionService.findEntityById(
                        () -> propertyRepository.findById(propertyId),
                        "매물 정보가 존재하지 않습니다. 관리자에게 문의하세요."
                );
                showingPropertyRepository.save(ShowingProperty.builder()
                        .clientId(client.getClientId())
                        .propertyId(property.getPropertyId())
                        .registrationManagerId(
                                entityExceptionService.findEntityById(
                                        () -> managerRepository.findById(clientForm.getManagerId()),
                                        "매니저 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                        ) // 등록자 id는 담당자 id로 init
                        .modifiedManagerId(
                                entityExceptionService.findEntityById(
                                        () -> managerRepository.findById(clientForm.getManagerId()),
                                        "매니저 정보가 존재하지 않습니다. 관리자에게 문의하세요.").getManagerId()
                        ) // 수정자 id는 담당자 id로 init
                        .build());
            }
        }
        return client.getClientId();
    }


    /**
     * 고객 가격 요약. ( 중복 코드 -> CommonService로 이동) deprecated
     */
//    private String getClientSummaryPrice(Property property) {
//        if (property.getTransactionType() == TransactionType.MONTHLY || property.getTransactionType() == TransactionType.SHORTERM)
//            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getDeposit(), property.getMonthlyFee());
//        else if (property.getTransactionType() == TransactionType.JEONSE)
//            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getJeonseFee());
//        else if (property.getTransactionType() == TransactionType.TRADE)
//            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getTradeFee());
//        else
//            return null;
//    }
}
