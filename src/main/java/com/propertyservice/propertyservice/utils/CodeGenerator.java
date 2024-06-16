package com.propertyservice.propertyservice.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class CodeGenerator {
    public String generateCompanyCode(String addressLevel1Code, String addressLevel2Code, String bizNumber){
        Random random = new Random(Long.parseLong(bizNumber));
//        Todo : 코드 생성 로직 변경
//        1. 회사 주소의 영문 코드값 가져옴.
//        2. 사업자등록번호를 시드로 랜덤 번호 4자리 완성
//        3. 완성된 회사코드 중복 검증
        return addressLevel1Code.toUpperCase() + addressLevel2Code.toUpperCase() + random.nextInt(10000)+1;
    }
}
