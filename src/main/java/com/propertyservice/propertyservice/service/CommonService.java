package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.common.AddressLevel1;
import com.propertyservice.propertyservice.domain.common.AddressLevel2;
import com.propertyservice.propertyservice.domain.common.Gender;
import com.propertyservice.propertyservice.domain.common.TransactionType;
import com.propertyservice.propertyservice.domain.manager.ManagerState;
import com.propertyservice.propertyservice.dto.common.*;
import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
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
public class CommonService {

    private final AddressLevel1Repository addressLevel1Repository;
    private final AddressLevel2Respository addressLevel2Respository;



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

    public List<TransactionTypeDto> searchTransactionTypeList() {
        return Arrays.stream(TransactionType.values())
                .map(transactionType -> new TransactionTypeDto(transactionType.name(), transactionType.getLabel()))
                .collect(Collectors.toList());
    }


    public List<ManagerStateDto> searchManagerStateList(){
        return Arrays.stream(ManagerState.values())
                .map(managerState -> new ManagerStateDto(managerState.name(), managerState.getLabel()))
                .collect(Collectors.toList());
    }
    public List<GenderDto> searchGenderList() {
        return Arrays.stream(Gender.values())
                .map(gender -> new GenderDto(gender.name(), gender.getLabel()))
                .collect(Collectors.toList());
    }
    /**
        public List<GenderDto> searchGenderList() {
        List<GenderDto> genderDtoList = new ArrayList<>();
        for (Gender gender : genderRepository.findAll()) {
            genderDtoList.add(GenderDto.builder()
                    .genderId(gender.getGenderId())
                    .gender(gender.getGender())
                    .build());
        }
        return genderDtoList;
    }

    public List<ManagerStateDto> searchManagerStateList() {
        List<ManagerStateDto> managerStateDtoList = new ArrayList<>();
        for (ManagerState managerState : managerStateRepository.findAll()) {
            managerStateDtoList.add(ManagerStateDto.builder()
                    .managerStateId(managerState.getManagerStateId())
                    .managerState(managerState.getManagerState())
                    .build());
        }
        return managerStateDtoList;
    }
     **/
}

