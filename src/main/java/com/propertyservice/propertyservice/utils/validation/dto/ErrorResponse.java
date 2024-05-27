package com.propertyservice.propertyservice.utils.validation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorResponse {

    @JsonProperty("status_code")
    private String statusCode;
}
