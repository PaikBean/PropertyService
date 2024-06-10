package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.common.*;
import com.propertyservice.propertyservice.domain.company.ManagerState;
import com.propertyservice.propertyservice.dto.common.AddressLevel1Dto;
import com.propertyservice.propertyservice.dto.common.AddressLevel2Dto;
import com.propertyservice.propertyservice.dto.common.TransactionTypeDto;
import com.propertyservice.propertyservice.repository.common.*;
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
public class CommonService {

    private final AddressLevel1Repository addressLevel1Repository;
    private final AddressLevel2Respository addressLevel2Respository;
    private final TransactionTypeRepository transactionTypeRepository;
//    private final GenderRepository genderRepository;
    private final ManagerStateRepository managerStateRepository;

    public List<AddressLevel1Dto> getAddressLevel1List() {
        List<AddressLevel1Dto> addressLevel1DtoList = new ArrayList<>();
        for (AddressLevel1 addressLevel1 : addressLevel1Repository.findAll()) {
            addressLevel1DtoList.add(AddressLevel1Dto.builder()
                    .addressLevel1(addressLevel1)
                    .build());
        }
        return addressLevel1DtoList;
    }

    public List<AddressLevel2Dto> getAddressLevel2List(Long addressLevel1Id) {
        List<AddressLevel2Dto> addressLevel2DtoList = new ArrayList<>();
        for (AddressLevel2 addressLevel2 : addressLevel2Respository.findByAddressLevel1_AddressLevel1Id(addressLevel1Id)) {
            addressLevel2DtoList.add(AddressLevel2Dto.builder()
                    .addressLevel2(addressLevel2)
                    .build());
        }
        return addressLevel2DtoList;
    }

    public List<TransactionTypeDto> searchInflowTypeList() {
        List<TransactionTypeDto> transactionTypeDtoList = new ArrayList<>();
        for (TransactionType type : transactionTypeRepository.findAllByIsUsed(true)) {
            transactionTypeDtoList.add(TransactionTypeDto.builder()
                    .transactionTypeId(type.getTransactionTypeId())
                    .transactionTypeCode(type.getTransactionTypeCode())
                    .transactionTypeName(type.getTransactionTypeName())
                    .build());
        }
        return transactionTypeDtoList;
    }

//    public List<GenderDto> searchGenderList() {
//        List<GenderDto> genderDtoList = new ArrayList<>();
//        for (Gender gender : genderRepository.findAll()) {
//            genderDtoList.add(GenderDto.builder()
//                    .genderId(gender.getGenderId())
//                    .gender(gender.getGender())
//                    .build());
//        }
//        return genderDtoList;
//    }

    public List<ManagerStateDto> searhManagerStateList() {
        List<ManagerStateDto> managerStateDtoList = new ArrayList<>();
        for (ManagerState managerState : managerStateRepository.findAll()) {
            managerStateDtoList.add(ManagerStateDto.builder()
                    .mangerStateId(managerState.getManagerStateId())
                    .managerState(managerState.getManagerState())
                    .build());
        }
        return managerStateDtoList;
    }

    ;
}

