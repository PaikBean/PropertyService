package com.propertyservice.propertyservice.dto.client;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShowingPropertyForm {
    private Long clientId;
    private Long propertyId;
    private List<Long> showingPropertyList; //수정자.
}
