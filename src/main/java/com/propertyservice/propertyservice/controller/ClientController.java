package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.client.Client;
import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.domain.manager.Manager;
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
            List<ShowingPropertyCandidateDto> showingPropertyCandidateDto = clientService.searchShowingPropertyCandidateList(showingPropertyCandidateCondition);
            return showingPropertyCandidateDto.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, showingPropertyCandidateDto, "204")
                    : new Response(ResponseCode.SUCCESS, showingPropertyCandidateDto, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 보여줄 매물 등록.
     * @param showingProrpertyForm
     * @return
     */
    @PostMapping("/v1/showing-property")
    public Response createShowingProperty(@RequestBody ShowingProrpertyForm showingProrpertyForm){
        try {
            return new Response(ResponseCode.SUCCESS, clientService.createShowingProrperty(showingProrpertyForm), "201");
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
            clientService.deleteShowingProperty(showingPropertyId);
            return new Response(ResponseCode.SUCCESS, null, "200");
        } catch (Exception e) {
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }


    /**
     * 고객 등록.
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
     * 고객 정보 단건 조회 - 고객 관리
     * @return
     */
    @GetMapping("/v1/client/{clientId}")
    public Response searchClientInfo(@PathVariable("clientId") Long clientId){
        try{
            return new Response(ResponseCode.SUCCESS, clientService.searchClientInfo(clientId), "201");

        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 고객 특이사항 등록.
     * @param clientRemarkForm
     * @return
     */
    @PostMapping("/v1/client-remark")
    public Response createClientRemark(@RequestBody ClientRemarkForm clientRemarkForm){
        try{
            return new Response(ResponseCode.SUCCESS, clientService.createClientRemark(clientRemarkForm), "201");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "404");
        }
    }

    /**
     * 고객 특이사항 제거
     * @param clientRemarkId
     * @return
     */
    @DeleteMapping("/v1/client-remark/{clientRemarkId}")
    public Response deleteClientRemark(@PathVariable(name = "clientRemarkId")Long clientRemarkId){
        try{
            clientService.deleteClientRemark(clientRemarkId);
            return new Response(ResponseCode.SUCCESS, null, "200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "404");
        }
    }

    /**
     * 고객 정보 수정
     * @param clientForm
     * @return
     */
    @PutMapping("/v1/client/")
    public Response updateClient(@RequestBody ClientForm clientForm){
        try{
            return new Response(ResponseCode.SUCCESS, clientService.updateClient(clientForm), "200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "404");
        }
    }

    /**
     * 고객 상세 정보.
     * @param clientDetailCondition
     * @return
     */
    @GetMapping("/v1/client-detail")
    public Response searchClientDetail(ClientCondition.clientDetailCondition clientDetailCondition){
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




    @GetMapping("/v1/client-list/{companyId}")
    public Response searchClientList(@PathVariable("companyId")Long companyId){
        try{
            List<ClientDto.ClientListDto> clientList = clientService.searchClientList(companyId);
            return clientList.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, clientList, "204")
                    : new Response(ResponseCode.SUCCESS, clientList, "200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

}
