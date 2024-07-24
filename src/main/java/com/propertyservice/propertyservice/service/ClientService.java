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
import com.propertyservice.propertyservice.repository.property.PropertyRepository;
import com.propertyservice.propertyservice.utils.SummaryPrice;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    //private final InflowTypeRepository inflowTypeRepository;
    private final PropertyRepository propertyRepository;
    private final ScheduleService scheduleService;
    private final ManagerService managerService;
    private final CompanyService companyService;

    public List<InflowTypeDto> searchInflowTypeList() {
        return Arrays.stream(InflowType.values())
                .map(inflowType -> new InflowTypeDto(inflowType.name(), inflowType.getLabel()))
                .collect(Collectors.toList());
    }

    public List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
        List<ShowingPropertyCandidateDto> showingPropertyCandidateDtoList = propertyRepository.searchShowingPropertyCandidateList(showingPropertyCandidateCondition);
        for (ShowingPropertyCandidateDto showingPropertyCandidateDto : showingPropertyCandidateDtoList) {
            Property property = propertyRepository.findById(showingPropertyCandidateDto.getPropertyId()).orElseThrow(
                    () -> new EntityNotFoundException("정보가 잘못되었습니다. 관리자에게 문의하세요."));
            showingPropertyCandidateDto.setPrice(getClientSummaryPrice(property));
        }
        return showingPropertyCandidateDtoList;
    }

    public Long createShowingProrperty(ShowingProrpertyForm showingProrpertyForm ){
        return showingPropertyRepository.save(ShowingProperty.builder()
                .clientId(clientRepository.findById(showingProrpertyForm.getClientId()).orElseThrow(
                                () -> new EntityNotFoundException("고객 정보를 찾을수 없습니다.")
                        ).getClientId()
                )
                .propertyId(propertyRepository.findById(showingProrpertyForm.getClientId()).orElseThrow(
                                () -> new EntityNotFoundException("매물 정보를 찾을 수 없습니다.")
                        ).getPropertyId()
                )
                .registrationManagerId(managerService.searchManagerIdById(showingProrpertyForm.getManagerId()))
                .modifiedManagerId(managerService.searchManagerIdById(showingProrpertyForm.getManagerId()))
                .build()).getClientId();
    }
    public void deleteShowingProperty(Long showingPropertyId){
        ShowingProperty showingProperty = showingPropertyRepository.findById(showingPropertyId).orElseThrow(
                () -> new EntityNotFoundException("보여줄 매물 정보를 찾을 수 없습니다.")
        );
        showingPropertyRepository.delete(showingProperty);
    }

    private String getClientSummaryPrice(Property property) {
        if (property.getTransactionType() == TransactionType.MONTHLY || property.getTransactionType() == TransactionType.SHORTERM)
            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getDeposit(), property.getMonthlyFee());
        else if (property.getTransactionType() == TransactionType.JEONSE)
            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getJeonseFee());
        else if (property.getTransactionType() == TransactionType.TRADE)
            return SummaryPrice.summaryPrice(property.getTransactionType().name(), property.getTradeFee());
        else
            return null;
    }

    public Long createClient(ClientForm clientForm){
        Client client =   clientRepository.save(Client.builder()
                .clientName(clientForm.getClientName())
                .clientPhoneNumber(clientForm.getClientPhoneNumber())
                .managerId(managerService.searchManagerIdById(clientForm.getManagerId()))
                .inflowType(clientForm.getInflowType())
                .registrationManagerId(managerService.searchManagerIdById(clientForm.getManagerId())) // 등록자 id는 담당자 id로 init
                .modifiedManagerId(managerService.searchManagerIdById(clientForm.getManagerId())) // 수정자 id는 담당자 id로 init
//                .company(companyService.searchCompany(clientForm.getCompanyId()))
                .build());
        if(clientForm.getRemark() != null){
            clientRemarkRepository.save(ClientRemark.builder()
                    .clientId(client.getClientId())
                    .remark(clientForm.getRemark())
                    .build());
        }
        if(clientForm.getPropertyList()!= null) {
            for (Property property : clientForm.getPropertyList()) {
                showingPropertyRepository.save(ShowingProperty.builder()
                        .clientId(client.getClientId())
                        .propertyId(propertyRepository.findById(property.getPropertyId()).orElseThrow(
                                        () -> new EntityNotFoundException("매물 정보를 찾을 수 없습니다.")
                                ).getPropertyId()
                        )
                        .registrationManagerId(managerService.searchManagerIdById(clientForm.getManagerId())) // 등록자 id는 담당자 id로 init
                        .modifiedManagerId(managerService.searchManagerIdById(clientForm.getManagerId())) // 수정자 id는 담당자 id로 init
                        .build());
            }
        }
        return client.getClientId();
    }

    public Long createClientRemark(ClientRemarkForm clientRemarkForm){
        return  clientRemarkRepository.save(ClientRemark.builder()
                .clientId(clientRepository.findById(clientRemarkForm.getClientId()).orElseThrow( () ->
                        new EntityNotFoundException("고객 정보가 없습니다.")).getClientId())
                .remark(clientRemarkForm.getRemark())
                .build()).getClientId();
    }



    public List<ClientDto.ClientListResponseDto> searchClientList(ClientCondition.clientListCondition clientListCondition){
        return clientRepository.searchClientList(clientListCondition.getManagerId(), clientListCondition.getClientPhoneNumber());
    }


    public ClientDetailDto searchClientDetailList(ClientCondition.clientDetailCondition clientDetailCondition){
        //고객 일정
        List<ScheduleSummaryDto> scheduleSummaryDtoList = scheduleService.searchScheduleList(clientDetailCondition.getClientId());

        // 고객 매물
        List<ShowingPropertySummaryDto> showingPropertySummaryDtoList = clientRepository.searchShowingPropertyList(clientDetailCondition.getClientId(), clientDetailCondition.getPropertyId());

        // 고객 특이사항.
        List<ClientRemarkDto> clientRemarkDtoList = clientRepository.searchClientRemark(clientDetailCondition.getClientId());

        return ClientDetailDto.builder()
                .clientId(clientDetailCondition.getClientId())
                .propertyId(clientDetailCondition.getPropertyId())
                .scheduleList(scheduleSummaryDtoList)
                .showingPropertyList(showingPropertySummaryDtoList)
                .clientRemarkList(clientRemarkDtoList)
                .build();
    }

    public List<ClientRemarkDto> searchClientRemarkList(Long clientId){
        return clientRepository.searchClientRemark(clientId);
    }

    public void deleteClientRemark(Long clientRemarkId){
        ClientRemark clientRemark = clientRemarkRepository.findById(clientRemarkId).orElseThrow(
                () -> new EntityNotFoundException("해당 특이사항이 존재 하지 않습니다.")
        );
        clientRemarkRepository.delete(clientRemark);
    }

    public List<ClientDto.ClientListDto> searchClientList(Long companyId){
//        Company company = companyService.searchCompany(companyId);
        return clientRepository.searchClientList(companyId);
    }
}
