package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.client.InflowType;
import com.propertyservice.propertyservice.dto.client.InflowTypeDto;
import com.propertyservice.propertyservice.repository.client.InflowTypeRepository;
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

    private final InflowTypeRepository inflowTypeRepository;

    public List<InflowTypeDto> searchInflowTypeList() {
        List<InflowTypeDto> inflowTypeDtoList = new ArrayList<>();

        for (InflowType inflowType : inflowTypeRepository.findAll()) {
            inflowTypeDtoList.add(InflowTypeDto.builder()
                    .inflowType(inflowType)
                    .build());
        }

        return inflowTypeDtoList;
    }
}
