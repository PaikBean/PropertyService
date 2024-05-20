package com.propertyservice.propertyservice.dto.address;

import com.propertyservice.propertyservice.domain.address.AddressLevel1;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddressLevel1Dto {
    private Long addressLevel1Id;
    private String addressLevel1;

    @Builder
    public AddressLevel1Dto(AddressLevel1 addressLevel1) {
        this.addressLevel1Id = addressLevel1.getAddressLevel1Id();
        this.addressLevel1 = addressLevel1.getAddressLevel1();
    }
}
