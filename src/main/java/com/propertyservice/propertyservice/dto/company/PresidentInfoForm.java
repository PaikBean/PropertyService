package com.propertyservice.propertyservice.dto.company;

import com.propertyservice.propertyservice.domain.common.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresidentInfoForm {
    private String presidentName;
    private String presidentRank;
    private String presidentPosition;
    private Long presidentStateId;
    private Gender presidentGender;
    private String presidentPhoneNumber;
    private String presidentEmail;
    private String presidentPassword;

    @Builder
    public PresidentInfoForm(String presidentName, String presidentRank, String presidentPosition, Long presidentStateId, Gender presidentGender, String presidentPhoneNumber, String presidentEmail, String presidentPassword) {
        this.presidentName = presidentName;
        this.presidentRank = presidentRank;
        this.presidentPosition = presidentPosition;
        this.presidentStateId = presidentStateId;
        this.presidentGender = presidentGender;
        this.presidentPhoneNumber = presidentPhoneNumber;
        this.presidentEmail = presidentEmail;
        this.presidentPassword = presidentPassword;
    }
}
