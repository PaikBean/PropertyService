package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.client.ClientDto;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateCondition;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateDto;
import com.propertyservice.propertyservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    /**
     * 유입경로 목록 조회
     *
     * @return
     */
    @GetMapping("/v1/inflow-type-list")
    public Response searchInflowTypeList() {
        try {
            return new Response(ResponseCode.SUCCESS, clientService.searchInflowTypeList(), "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 보여줄 매물 후보 목록 조회
     *
     * @param showingPropertyCandidateCondition
     * @return
     */
    @GetMapping("/v1/showing-property-list")
    public Response searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition) {
        try {
            List<ShowingPropertyCandidateDto> showingPropertyCandidateDtos = clientService.searchShowingPropertyCandidateList(showingPropertyCandidateCondition);
            return showingPropertyCandidateDtos.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, showingPropertyCandidateDtos, "204")
                    : new Response(ResponseCode.SUCCESS, showingPropertyCandidateDtos, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 고객 장
     * @param clientDto
     * @return
     */
    @PostMapping("/v1/client")
    public Response createClient(ClientDto clientDto){
        try{
            return clientDto.getRemark() == null
                    ? new Response(ResponseCode.SUCCESS, clientService.createClientRemark(clientService.createClientLedger(clientDto), clientDto.getRemark()), "201")
                    : new Response(ResponseCode.SUCCESS, clientService.createClientLedger(clientDto), "201");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
