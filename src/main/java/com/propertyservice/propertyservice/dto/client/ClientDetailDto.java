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
    private Long propertyId;

    List<ScheduleSummaryDto> scheduleList;
    List<ShowingPropertySummaryDto> showingPropertyList;
    List<ClientRemarkDto> clientRemarkList;

    @Builder
    public ClientDetailDto(Long clientId, Long propertyId, List<ScheduleSummaryDto> scheduleList, List<ShowingPropertySummaryDto> showingPropertyList, List<ClientRemarkDto> clientRemarkList){
        this.clientId = clientId;
        this.propertyId = propertyId;
        this.scheduleList =scheduleList;
        this.showingPropertyList = showingPropertyList;
        this.clientRemarkList = clientRemarkList;
    }
}
