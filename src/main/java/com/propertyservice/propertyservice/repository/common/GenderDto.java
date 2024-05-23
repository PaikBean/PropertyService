package com.propertyservice.propertyservice.repository.common;

import com.propertyservice.propertyservice.domain.common.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GenderDto {
    private Long genderId;
    private String gender;

    @Builder
    public GenderDto(Long genderId, String gender) {
        this.genderId = genderId;
        this.gender = gender;
    }
}
