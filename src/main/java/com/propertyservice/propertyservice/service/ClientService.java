package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.client.Client;
import com.propertyservice.propertyservice.domain.client.ClientRemark;
import com.propertyservice.propertyservice.domain.client.InflowType;
import com.propertyservice.propertyservice.domain.property.Property;

import com.propertyservice.propertyservice.domain.property.ShowingProperty;
import com.propertyservice.propertyservice.dto.client.*;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;
import com.propertyservice.propertyservice.repository.client.*;
import com.propertyservice.propertyservice.repository.common.TransactionTypeRepository;
import com.propertyservice.propertyservice.repository.property.PropertyRepository;
import com.propertyservice.propertyservice.utils.SummaryPrice;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientRemarkRepository clientRemarkRepository;
    private final ShowingPropertyRepository showingPropertyRepository;
    private final InflowTypeRepository inflowTypeRepository;
    private final PropertyRepository propertyRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final ScheduleService scheduleService;
    private final ManagerService managerService;
    public List<InflowTypeDto> searchInflowTypeList() {
        List<InflowTypeDto> inflowTypeDtoList = new ArrayList<>();

        for (InflowType inflowType : inflowTypeRepository.findAll()) {
            inflowTypeDtoList.add(InflowTypeDto.builder()
                    .inflowType(inflowType)
                    .build());
        }

        return inflowTypeDtoList;
    }

    public List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
        List<ShowingPropertyCandidateDto> showingPropertyCandidateDtoList = propertyRepository.searchShowingPropertyCandidateList(showingPropertyCandidateCondition);
        for (ShowingPropertyCandidateDto showingPropertyCandidateDto : showingPropertyCandidateDtoList) {
            Property property = propertyRepository.findById(showingPropertyCandidateDto.getPropertyId()).orElseThrow(
                    () -> new EntityNotFoundException("정보가 잘못되었습니다. 관리자에게 문의하세요."));
            showingPropertyCandidateDto.setPrice(getSummaryPrice(property));
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
                .registrationManagerId(managerService.searchManagerById(showingProrpertyForm.getManagerId()))
                .modifiedManagerId(managerService.searchManagerById(showingProrpertyForm.getManagerId()))
                .build()).getClientId();
    }
    public void deleteShowingProperty(Long showingPropertyId){
        ShowingProperty showingProperty = showingPropertyRepository.findById(showingPropertyId).orElseThrow(
                () -> new EntityNotFoundException("보여줄 매물 정보를 찾을 수 없습니다.")
        );
        showingPropertyRepository.delete(showingProperty);
    }

    private String getSummaryPrice(Property property) {
        if (property.getTransactionTypeId() == 1 || property.getTransactionTypeId() == 4)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getTransactionTypeId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getDeposit(), property.getMonthlyFee());
        else if (property.getTransactionTypeId() == 2)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getTransactionTypeId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getJeonseFee());
        else if (property.getTransactionTypeId() == 3)
            return SummaryPrice.summaryPrice(transactionTypeRepository.findById(property.getTransactionTypeId()).orElseThrow(
                    () -> new EntityNotFoundException("선택한 거래유형을 찾을 수 없습니다. 관리자에게 문의하세요")
            ).getTransactionTypeName(), property.getTradeFee());
        else
            return null;
    }

    public Long createClient(ClientForm clientForm){
        Client client =   clientRepository.save(Client.builder()
                .clientName(clientForm.getClientName())
                .clientPhoneNumber(clientForm.getClientPhoneNumber())
                .managerId(managerService.searchManagerById(clientForm.getManagerId()))
                .inflowTypeId(inflowTypeRepository.findById(clientForm.getInflowTypeId()).orElseThrow(
                        () -> new IllegalStateException("inflowType 정보를 찾을 수 없습니다.")
                    ).getId()
                )
                .registrationManagerId(managerService.searchManagerById(clientForm.getManagerId())) // 등록자 id는 담당자 id로 init
                .modifiedManagerId(managerService.searchManagerById(clientForm.getManagerId())) // 수정자 id는 담당자 id로 init
                .build());
        if(clientForm.getRemark() != null){
            clientRemarkRepository.save(ClientRemark.builder()
                    .clientId(client.getClientId())
                    .remark(clientForm.getRemark())
                    .build());
        }

        for (Property property : clientForm.getPropertyList()){
            showingPropertyRepository.save(ShowingProperty.builder()
                            .clientId(client.getClientId())
                            .propertyId(propertyRepository.findById(property.getPropertyId()).orElseThrow(
                                    () -> new EntityNotFoundException("매물 정보를 찾을 수 없습니다.")
                                    ).getPropertyId()
                            )
                            .registrationManagerId(managerService.searchManagerById(clientForm.getManagerId())) // 등록자 id는 담당자 id로 init
                            .modifiedManagerId(managerService.searchManagerById(clientForm.getManagerId())) // 수정자 id는 담당자 id로 init
                    .build());
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
}
