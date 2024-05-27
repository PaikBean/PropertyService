package com.propertyservice.propertyservice.utils.validation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BizNumberValidateResponseDto {

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("request_cnt")
    private int requestCnt;

    @JsonProperty("valid_cnt")
    private int validCnt;

    @JsonProperty("data")
    private List<BizNumberData> data;

    @Data
    public static class BizNumberData {

        @JsonProperty("b_no")
        private String bNo;

        @JsonProperty("valid")
        private String valid;

        @JsonProperty("valid_msg")
        private String validMsg;

        @JsonProperty("request_param")
        private RequestParam requestParam;

        @JsonProperty("status")
        private Status status;

        @Data
        public static class RequestParam {
            @JsonProperty("b_no")
            private String bNo;

            @JsonProperty("start_dt")
            private String startDt;

            @JsonProperty("p_nm")
            private String pNm;

            @JsonProperty("p_nm2")
            private String pNm2;

            @JsonProperty("b_nm")
            private String bNm;

            @JsonProperty("corp_no")
            private String corpNo;

            @JsonProperty("b_sector")
            private String bSector;

            @JsonProperty("b_type")
            private String bType;

            @JsonProperty("b_adr")
            private String bAdr;
        }

        @Data
        public static class Status {
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
}
