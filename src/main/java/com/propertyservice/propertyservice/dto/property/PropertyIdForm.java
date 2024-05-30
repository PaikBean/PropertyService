package com.propertyservice.propertyservice.dto.property;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PropertyIdForm {
    @NotNull
    private Long propertyId;
}
