package com.propertyservice.propertyservice.dto.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowingProrpertyForm {
    private Long clientId;
    private Long propertyId;
    private Long managerId; //수정자.
}
