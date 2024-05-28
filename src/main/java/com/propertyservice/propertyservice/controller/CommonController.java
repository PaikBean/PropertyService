package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.common.AddressLevel2Dto;
import com.propertyservice.propertyservice.service.CommonService;
import com.propertyservice.propertyservice.utils.validation.ValidBizRegNumber;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberValidateRequestForm;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberStatusRequestForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class CommonController {

    private final CommonService commonService;

    /**
     * 시/도 주소 목록 조회
     *
     * @return
     */
    @GetMapping("/v1/address-level1-list")
    public Response getAddressLevel1List() {
        try {
            return new Response(ResponseCode.SUCCESS, commonService.getAddressLevel1List(), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 시/구/동 주소 목록 조회
     *
     * @param addressLevel1Id
     * @return
     */
    @GetMapping("/v1/address-level2-list/{addressLevel1Id}")
    public Response getAddressLevel2List(@PathVariable("addressLevel1Id") Long addressLevel1Id) {
        try {
            List<AddressLevel2Dto> addressLevel2List = commonService.getAddressLevel2List(addressLevel1Id);
            return addressLevel2List.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, addressLevel2List, "204")
                    : new Response(ResponseCode.SUCCESS, addressLevel2List, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 거래유형 목록 조회
     *
     * @return
     */
    @GetMapping("/v1/transaction-type-list")
    public Response searchInflowTypeList() {
        try {
            return new Response(ResponseCode.SUCCESS, commonService.searchInflowTypeList(), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 성별 목록 조회
     *
     * @return
     */
    @GetMapping("/v1/gender-list")
    public Response searchGenderList() {
        try {
            return new Response(ResponseCode.SUCCESS, commonService.searchGenderList(), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 근무 상태 목록 조회
     *
     * @return
     */
    @GetMapping("/v1/manager-state-list")
    public Response searchManagerStateList() {
        try {
            return new Response(ResponseCode.SUCCESS, commonService.searhManagerStateList(), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 사업자등록번호 검증
     *
     * @param bIzNumberValidateRequestForm
     * @return
     */
    @GetMapping("/v1/valid/biz-number-validate")
    public Response validBizNumberValidate(BizNumberValidateRequestForm bIzNumberValidateRequestForm) {
        try {
            return ValidBizRegNumber.validateBizNumber(bIzNumberValidateRequestForm) ?
                    new Response(ResponseCode.SUCCESS, null, "200")
                    : new Response(ResponseCode.SUCCESS, null, "204");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 사업자등록번호 상태 조회
     *
     * @param bizNumberStatusRequestForm
     * @return
     */
    @GetMapping("/v1/valid/biz-number-status")
    public Response validBizNumberStatus(BizNumberStatusRequestForm bizNumberStatusRequestForm) {
        try {
            return ValidBizRegNumber.statusBizNumber(bizNumberStatusRequestForm) ?
                    new Response(ResponseCode.SUCCESS, null, "200")
                    : new Response(ResponseCode.SUCCESS, null, "204");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
