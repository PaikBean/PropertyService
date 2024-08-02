package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.building.*;
import com.propertyservice.propertyservice.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
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
     * 건물/임대인 등록
     *
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

    /**
     * 건물 특이사항 등록
     *
     * @param buildingRemarkForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/v1/building-remark")
    public Response createBuildingRemark(@RequestBody @Valid BuildingRemarkForm buildingRemarkForm, BindingResult bindingResult) {
        try {
            return new Response(ResponseCode.SUCCESS, buildingService.createBuildingRemark(buildingRemarkForm), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 건물 특이사항 제거
     * @return
     */
    @DeleteMapping("/v1/building-remark/{buildingRemarkId}")
    public Response deleteBuildingRemark(@PathVariable("buildingRemarkId") Long buildingRemarkId) {
        try {
            buildingService.deleteBuildingRemark(buildingRemarkId);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 건물 상세 및 매물 목록 조회
     * @param buildingId
     * @return
     */
    @GetMapping("/v1/building-property-list/{buildingId}")
    public Response searchBuildingPropertyList(@PathVariable(name = "buildingId") Long buildingId) {
        try {
            return new Response(ResponseCode.SUCCESS, buildingService.searchBuildingPropertyList(buildingId), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 건물 상세 단건 조회 - 건물 관리
     * @param buildingId0
     * @return
     */
    @GetMapping("/v1/building-info/{buildingId}")
    public Response searchBuildingInfo(@PathVariable("buildingId")Long buildingId0){
        try {
            return new Response(ResponseCode.SUCCESS, buildingService.searchBuildingInfo(buildingId0), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 건물 상세 수정
     * @param buildingPropertyForm
     * @param bindingResult
     * @return
     */
    @PutMapping("/v1/building-info/")
    public Response updateBuildingDetail(@RequestBody @Valid BuildingPropertyForm buildingPropertyForm, BindingResult bindingResult){
        try {
            return new Response(ResponseCode.SUCCESS, buildingService.updateBuildingDetail(buildingPropertyForm), "200");
        } catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 건물 특이사항 목록 조회
     *
     * @param buildingId
     * @return
     */
    @GetMapping("/v1/building-remark-list/{buildingId}")
    public Response searchBuildingRemarkList(@PathVariable(name = "buildingId") Long buildingId) {
        try {
            List<BuildingRemarkDto> buildingRemarkDtoList = buildingService.searchBuildingRemarkList(buildingId);
            return buildingRemarkDtoList.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, buildingRemarkDtoList, "204")
                    : new Response(ResponseCode.SUCCESS, buildingRemarkDtoList, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 건물 상세 단건 조회 - 매물 상세
     * @param buildingId
     * @return
     */
    @GetMapping("/v1/building-detail/{buildingId}")
    public Response searchBuildingDetail(@PathVariable("buildingId") Long buildingId){
        try {
            return new Response(ResponseCode.SUCCESS, buildingService.searchBuildingPropertyList(buildingId), "200");
        } catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
