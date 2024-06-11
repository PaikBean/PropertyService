package com.propertyservice.propertyservice.utils.validation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizNumberValidateRequestForm {
    @NotNull
    private String bNo;                 // 사업자 등록 번호
    @NotNull
    private String startDate;           // 개업일자 (YYYYMMDD 포맷)
    @NotNull
    private String pName;               // 대표자성명1
    private String pName2;              // 대표자성명2 (대표자성명1 이 한글이 아닌 경우, 이에 대한 한글 명) (Optional)
    private String bName;               // 상호 (Optional)
    private String corpNo;              // 법인등록번호 (Optional)
    private String bSector;             // 주업태명 (Optional)
    private String bType;               // 주종목명 (Optional)
    private String bAdr;                // 사업장주소 (Optional)
}
