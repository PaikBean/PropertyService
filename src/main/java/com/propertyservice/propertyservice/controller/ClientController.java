package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.client.*;
import com.propertyservice.propertyservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
     * @param clientForm
     * @return
     */
    @PostMapping("/v1/client")
    public Response createClient(@RequestBody ClientForm clientForm){
        try{
            return new Response(ResponseCode.SUCCESS, clientService.createClient(clientForm), "201");

        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 고객 목록 조회
     * @param clientListCondition
     * @return
     */
    @GetMapping("/v1/client-list")
    public Response searchClientList(ClientCondition.clientListCondition clientListCondition){
        try {
            List<ClientDto.ClientListResponseDto> clientListResponseDtoList = clientService.searchClientList(clientListCondition);
            return clientListResponseDtoList.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, clientListResponseDtoList, "204")
                    : new Response(ResponseCode.SUCCESS, clientListResponseDtoList, "200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 고객 상세 정보.
     * @param clientDetailCondition
     * @return
     */
    @GetMapping("/v1/client-detail")
    public Response searchClientDetailList(ClientCondition.clientDetailCondition clientDetailCondition){
        try{
            return new Response(ResponseCode.SUCCESS, clientService.searchClientDetailList(clientDetailCondition), "204");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 고객 특이사항 목록 조회
     * @param clientId
     * @return
     */
    @GetMapping("/v1/client-remark-list/{clientId}")
    public Response searchClientRemarkList(@PathVariable(name = "clientId")Long clientId){
        try{
            List<ClientRemarkDto> clientRemarkDtoList = clientService.searchClientRemarkList(clientId);
            return clientRemarkDtoList.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, clientRemarkDtoList, "204")
                    : new Response(ResponseCode.SUCCESS, clientRemarkDtoList, "200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

}
