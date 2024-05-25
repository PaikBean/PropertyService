package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.building.BuildingCondition;
import com.propertyservice.propertyservice.dto.building.BuildingDto;
import com.propertyservice.propertyservice.dto.building.BuildingOwnerForm;
import com.propertyservice.propertyservice.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/building")
public class BuildingController {

    private final BuildingService buildingService;

    /**
     * 조건에 따른 건물 목록 조회
     *
     * @param buildingCondition
     * @return
     */
    @GetMapping("/v1/building-list")
    public Response searchBuildingList(BuildingCondition buildingCondition) {
        try {
            List<BuildingDto> buildingDtoList = buildingService.searchBuildingList(buildingCondition);
            return buildingDtoList.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, buildingDtoList, "204")
                    : new Response(ResponseCode.SUCCESS, buildingDtoList, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * @param buildingOwnerForm
     * @return
     */
    @PostMapping("/v1/building")
    public Response createBuilding(@RequestBody @Valid BuildingOwnerForm buildingOwnerForm) {
        try {
            buildingService.createBuilding(buildingOwnerForm);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
