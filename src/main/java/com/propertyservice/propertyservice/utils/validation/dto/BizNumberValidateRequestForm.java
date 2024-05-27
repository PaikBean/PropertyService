package com.propertyservice.propertyservice.utils.validation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizNumberValidateRequestForm {
    private String b_no;
    private String start_dt;
    private String p_nm;
    private String p_nm2;
    private String b_nm;
    private String corp_no;
    private String b_sector;
    private String b_type;
    private String b_adr;
}
