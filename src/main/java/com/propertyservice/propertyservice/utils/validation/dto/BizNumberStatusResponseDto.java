package com.propertyservice.propertyservice.utils.validation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BizNumberStatusResponseDto {
    @JsonProperty("request_cnt")
    private int requestCnt;

    @JsonProperty("match_cnt")
    private int matchCnt;

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("data")
    private List<BizNumberStatus> data;

    @Data
    public static class BizNumberStatus {
        @JsonProperty("b_no")
        private String bNo;

        @JsonProperty("b_stt")
        private String bStt;

        @JsonProperty("b_stt_cd")
        private String bSttCd;

        @JsonProperty("tax_type")
        private String taxType;

        @JsonProperty("tax_type_cd")
        private String taxTypeCd;

        @JsonProperty("end_dt")
        private String endDt;

        @JsonProperty("utcc_yn")
        private String utccYn;

        @JsonProperty("tax_type_change_dt")
        private String taxTypeChangeDt;

        @JsonProperty("invoice_apply_dt")
        private String invoiceApplyDt;

        @JsonProperty("rbf_tax_type")
        private String rbfTaxType;

        @JsonProperty("rbf_tax_type_cd")
        private String rbfTaxTypeCd;
    }
}
