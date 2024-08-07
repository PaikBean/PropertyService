package com.propertyservice.propertyservice.dto.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowingPropertyForm {
    private Long clientId;
    private Long propertyId;
//    private Long managerId; //수정자.
}
