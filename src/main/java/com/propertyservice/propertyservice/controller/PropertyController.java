package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyForm;
import com.propertyservice.propertyservice.dto.property.PropertyForm;
import com.propertyservice.propertyservice.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyService propertyService;


    /**
     * 보여줄 매물 등록.
     * @param showingPropertyForm
     * @return
     */
    @PostMapping("/v1/showing-property")
    public Response createShowingProperty(@RequestBody ShowingPropertyForm showingPropertyForm){
        try {
            return new Response(ResponseCode.SUCCESS, propertyService.createShowingProrperty(showingPropertyForm), "201");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 보여줄 매물 삭제.
     * @param showingPropertyId
     * @return
     */
    @DeleteMapping("/v1/showing-property/{showingPropertyId}")
    public Response createShowingProperty(@PathVariable(name = "showingPropertyId")Long showingPropertyId){
        try {
            propertyService.deleteShowingProperty(showingPropertyId);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }



    /**
     * 매물 등록
     *
     * @param propertyForm
     * @return
     */
    @PostMapping("/v1/property")
    public Response createProperty(@RequestBody @Valid PropertyForm propertyForm) {
        try {
            propertyService.createProperty(propertyForm);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }


    /**
     * 매물 조회
     *
     * @param propertyId
     * @return
     */
    @GetMapping("/v1/property/{propertyId}")
    public Response searchProperty(@PathVariable(name = "propertyId") Long propertyId) {
        try {
            return new Response(ResponseCode.SUCCESS, propertyService.searchProperty(propertyId), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 매물 수정
     *
     * @param propertyForm
     * @return
     */
    @PutMapping("/v1/property")
    public Response updateProperty(@RequestBody @Valid PropertyForm propertyForm) {
        try {
            propertyService.updateProperty(propertyForm);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 매물 삭제
     * @return
     */
    @DeleteMapping("/v1/property/{propertyId}")
    public Response deleteProperty(@PathVariable("propertyId")Long propertyId) {
        try {
            propertyService.deleteProperty(propertyId);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 매물 유형 목록 조회
     * @return
     */
    @GetMapping("/v1/property-type-list")
    public Response searchPropertyTypeList(){
        try {
            return new Response(ResponseCode.SUCCESS, propertyService.searchPropertyTypeList(), "200");
        } catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
    /**
     * 매물 상세 정보 조회
     * @param propertyId
     * @return
     */
    @GetMapping("/v1/property-detail/{propertyId}")
    public Response searchPropertyDetail(@PathVariable("propertyId")Long propertyId){
        try {
            return new Response(ResponseCode.SUCCESS, propertyService.searchProperty(propertyId), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 보여줄 매물 목록 등록
     * @param showingPropertyForm
     * @return
     */
    @PostMapping("/v1/showing-property-list")
    public Response searchShowingPropertyList(@RequestBody ShowingPropertyForm showingPropertyForm){
        try {
            propertyService.createShowingProrperties(showingPropertyForm);
            return new Response(ResponseCode.SUCCESS, null, "201");
        } catch (Exception e) {
            log.info("Error! : {}", e.getMessage());
            log.info("Error! : {}", e.getClass());
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
