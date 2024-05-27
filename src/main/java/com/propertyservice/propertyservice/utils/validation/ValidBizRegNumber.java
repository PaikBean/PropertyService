package com.propertyservice.propertyservice.utils.validation;

import com.propertyservice.propertyservice.config.AppConfig;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberStatusRequestForm;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberStatusResponseDto;
import lombok.experimental.UtilityClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * 사업자 등록 번호 진위 확인
 * 공공데이터포털 API 활용
 * https://www.data.go.kr/data/15081808/openapi.do
 */

/*
    Todo :
     1. 해당 api status 검증 로직 구현
     2. 해당 api validate 구현
 */

@UtilityClass
public class ValidBizRegNumber {
    private String SERVICE_KEY;
    private String API_URL;
    private final String VALIDATE = "validate";
    private final String STATUS = "status";

    private static final HttpHeaders HTTP_HEADERS = new HttpHeaders();

    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        HTTP_HEADERS.set("Content-Type", "application/json; utf-8");
    }

    public void init(AppConfig appConfig) {
        SERVICE_KEY = appConfig.getServiceKey();
        API_URL = appConfig.getApiUrl();
    }

    public boolean statusBizNumber(BizNumberStatusRequestForm bizNumberStatusRequestForm) {
        validUrlKeyIsNull();
        try {
            URL url = new URL(API_URL + STATUS + "?serviceKey=" + SERVICE_KEY);

            HttpEntity<String> request = new HttpEntity<>(createBizNumberStatusRequestJson(bizNumberStatusRequestForm).toString(), HTTP_HEADERS);

            ResponseEntity<BizNumberStatusResponseDto> response = restTemplate.exchange(url.toURI(), HttpMethod.POST, request, BizNumberStatusResponseDto.class);

            return response.getStatusCode().is2xxSuccessful() && isStatusBizNumberAvail(response.getBody());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private boolean isStatusBizNumberAvail(BizNumberStatusResponseDto bizNumberStatusResponseDto) {
        if (!bizNumberStatusResponseDto.getData().get(0)
                .getTaxType().equals("국세청에 등록되지 않은 사업자등록번호입니다.")){
            if (bizNumberStatusResponseDto.getData().get(0).getBStt().equals("계속사업자")
                    && bizNumberStatusResponseDto.getData().get(0).getBSttCd().equals("01")
                    && isEndDateAfterCurrentDate(bizNumberStatusResponseDto.getData().get(0).getEndDt()))
                return true;
        }
        return false;
    }

    private boolean isEndDateAfterCurrentDate(String endDt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate endDate = LocalDate.parse(endDt, formatter);
        LocalDate currentDate = LocalDate.now();
        return endDate.isAfter(currentDate);
    }

    private JSONObject createBizNumberStatusRequestJson(BizNumberStatusRequestForm bizNumberStatusRequestForm) {
        JSONObject json = new JSONObject();
        JSONArray bNoArray = new JSONArray();
        bNoArray.put(bizNumberStatusRequestForm.getB_no());
        try {
            json.put("b_no", bNoArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private void validUrlKeyIsNull() {
        if (SERVICE_KEY == null || API_URL == null) {
            throw new IllegalStateException("SERVICE_KEY and API_URL must be initialized");
        }
    }
}
