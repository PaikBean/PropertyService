package com.propertyservice.propertyservice.dto.client;

import com.propertyservice.propertyservice.domain.client.ClientRemark;
import com.propertyservice.propertyservice.dto.schedule.ScheduleSummaryDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDetailDto {
    private Long clientId;
    private String clientName;
    private String inflowType;
    private String clientPhoneNumber;
    private Long managerId;

    List<ScheduleSummaryDto> scheduleList;
    List<ShowingPropertySummaryDto> showingPropertyList;
    List<ClientRemarkDto> clientRemarkList;

    @Builder
    public ClientDetailDto(Long clientId,String clientName, String inflowType, String clientPhoneNumber, Long managerId, List<ScheduleSummaryDto> scheduleList, List<ShowingPropertySummaryDto> showingPropertyList, List<ClientRemarkDto> clientRemarkList){
        this.clientId = clientId;
        this.clientName = clientName;
        this.inflowType = inflowType;
        this.clientPhoneNumber = clientPhoneNumber;
        this.managerId = managerId;
        this.scheduleList =scheduleList;
        this.showingPropertyList = showingPropertyList;
        this.clientRemarkList = clientRemarkList;
    }
}
