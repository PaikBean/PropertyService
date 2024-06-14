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
        private Long managerId;
        private String clientPhoneNumber;
    }

    /**
     * 고객 상세 조회 dto
     */
    @Getter
    @Setter
    public static class clientDetailCondition{
        private Long clientId;
        private Long propertyId;
    }

}
