package com.propertyservice.propertyservice.utils.validation;

import com.propertyservice.propertyservice.config.AppConfig;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberStatusRequestForm;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberStatusResponseDto;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberValidateRequestForm;
import com.propertyservice.propertyservice.utils.validation.dto.BizNumberValidateResponseDto;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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

@Slf4j
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

    public boolean validateBizNumber(BizNumberValidateRequestForm bizNumberValidateRequestForm){
        if(bizNumberValidateRequestForm.getBNo().equals("1234567890")) return true;     // 임시 사업자 등록번호
        validUrlKeyIsNull();
        try {
            URL url = new URL(API_URL + VALIDATE + "?serviceKey=" + SERVICE_KEY);

            HttpEntity<String> request = new HttpEntity<>(createBizNumberValidateRequestJson(bizNumberValidateRequestForm).toString(), HTTP_HEADERS);

            ResponseEntity<BizNumberValidateResponseDto> response = restTemplate.exchange(url.toURI(), HttpMethod.POST, request, BizNumberValidateResponseDto.class);
            log.info("validateBizNumber response is : {}", response.getBody().toString());
            return response.getStatusCode().is2xxSuccessful() && isValidateBizNumberAvail(response.getBody());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean statusBizNumber(BizNumberStatusRequestForm bizNumberStatusRequestForm) {
        validUrlKeyIsNull();
        try {
            URL url = new URL(API_URL + STATUS + "?serviceKey=" + SERVICE_KEY);

            HttpEntity<String> request = new HttpEntity<>(createBizNumberStatusRequestJson(bizNumberStatusRequestForm).toString(), HTTP_HEADERS);

            ResponseEntity<BizNumberStatusResponseDto> response = restTemplate.exchange(url.toURI(), HttpMethod.POST, request, BizNumberStatusResponseDto.class);
            log.info("statusBizNumber response is : {}", response.getBody().toString());
            return response.getStatusCode().is2xxSuccessful() && isStatusBizNumberAvail(response.getBody());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private boolean isValidateBizNumberAvail(BizNumberValidateResponseDto bizNumberValidateResponseDto) {
       return bizNumberValidateResponseDto.getData().get(0).getValid().equals("01")
               && (bizNumberValidateResponseDto.getData().get(0).getStatus().getBStt().equals("계속사업자")
               && (bizNumberValidateResponseDto.getData().get(0).getStatus().getBSttCd().equals("01"))
               && isEndDateAfterCurrentDate(bizNumberValidateResponseDto.getData().get(0).getStatus().getEndDt()));
    }

    private boolean isStatusBizNumberAvail(BizNumberStatusResponseDto bizNumberStatusResponseDto) {
        return !bizNumberStatusResponseDto.getData().get(0).getTaxType().equals("국세청에 등록되지 않은 사업자등록번호입니다.")
                && bizNumberStatusResponseDto.getData().get(0).getBStt().equals("계속사업자")
                && bizNumberStatusResponseDto.getData().get(0).getBSttCd().equals("01")
                && isEndDateAfterCurrentDate(bizNumberStatusResponseDto.getData().get(0).getEndDt());
    }

    private JSONObject createBizNumberStatusRequestJson(BizNumberStatusRequestForm bizNumberStatusRequestForm) {
        JSONObject json = new JSONObject();
        JSONArray bNoArray = new JSONArray();
        bNoArray.put(bizNumberStatusRequestForm.getBNo());
        try {
            json.put("b_no", bNoArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
    private JSONObject createBizNumberValidateRequestJson(BizNumberValidateRequestForm bizNumberValidateRequestForm){
        JSONObject json = new JSONObject();
        JSONObject businesses = new JSONObject();
        JSONArray businessesArray = new JSONArray();
        businessesArray.put(businesses);
        try {
            businesses.put("b_no", bizNumberValidateRequestForm.getBNo());
            businesses.put("start_dt", bizNumberValidateRequestForm.getStartDate());
            businesses.put("p_nm", bizNumberValidateRequestForm.getPName());
            businesses.put("p_nm2", bizNumberValidateRequestForm.getPName2());
            businesses.put("corp_no", bizNumberValidateRequestForm.getCorpNo());
            businesses.put("b_sector", bizNumberValidateRequestForm.getBSector());
            businesses.put("b_type", bizNumberValidateRequestForm.getBType());
            businesses.put("b_adr", bizNumberValidateRequestForm.getBAdr());
            json.put("businesses", businessesArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        log.info(json.toString());
        return json;
    }

    private boolean isEndDateAfterCurrentDate(String endDt){
        if (endDt == null || endDt.isEmpty()) return true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate endDate = LocalDate.parse(endDt, formatter);
        LocalDate currentDate = LocalDate.now();
        return !endDate.isAfter(currentDate);
    }

    private void validUrlKeyIsNull() {
        if (SERVICE_KEY == null || API_URL == null) {
            throw new IllegalStateException("SERVICE_KEY and API_URL must be initialized");
        }
    }
}
