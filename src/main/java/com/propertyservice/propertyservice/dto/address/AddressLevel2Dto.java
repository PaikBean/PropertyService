package com.propertyservice.propertyservice.dto.address;

import com.propertyservice.propertyservice.domain.address.AddressLevel2;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddressLevel2Dto {
    private Long addressLevel2Id;
    private String addressLevel2;

    @Builder
    public AddressLevel2Dto(AddressLevel2 addressLevel2) {
        this.addressLevel2Id = addressLevel2.getAddressLevel2Id();
        this.addressLevel2 = addressLevel2.getAddressLevel2();
    }
}
