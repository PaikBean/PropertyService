package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.address.AddressLevel1;
import com.propertyservice.propertyservice.domain.address.AddressLevel2;
import com.propertyservice.propertyservice.dto.address.AddressLevel1Dto;
import com.propertyservice.propertyservice.dto.address.AddressLevel2Dto;
import com.propertyservice.propertyservice.repository.address.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.address.AddressLevel2Respository;
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
public class AddressService {

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
}
