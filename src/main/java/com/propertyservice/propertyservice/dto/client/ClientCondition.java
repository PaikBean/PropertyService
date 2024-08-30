package com.propertyservice.propertyservice.dto.client;

import lombok.Getter;
import lombok.Setter;


public class ClientCondition {
    /**
     * 고객 목록 조회 dto
     */
    @Getter
    @Setter
    public static class clientListCondition{
        private String clientName;
        private String clientPhoneNumber;
    }


}
