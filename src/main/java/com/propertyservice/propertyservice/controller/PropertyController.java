package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.property.PropertyForm;
import com.propertyservice.propertyservice.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyService propertyService;

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

}
