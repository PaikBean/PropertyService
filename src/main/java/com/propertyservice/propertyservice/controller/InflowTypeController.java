package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.Response;
import com.propertyservice.propertyservice.domain.ResponseCode;
import com.propertyservice.propertyservice.repository.InflowTypeRepository;
import com.propertyservice.propertyservice.service.InflowTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InflowTypeController {

    private final InflowTypeService inflowTypeService;

    @GetMapping("/v1/inflow-type-list")
    public Response searchInflowTypeList(){
        try{
            return new Response(ResponseCode.SUCCESS, inflowTypeService.searchInflowTypeList());
        } catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
