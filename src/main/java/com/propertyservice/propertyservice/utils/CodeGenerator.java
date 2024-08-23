package com.propertyservice.propertyservice.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class CodeGenerator {

    private static final String COM_PREFIX = "CP";
    private static final String DEP_PREFIX ="DP";
    private static final Integer MAX_LENGTH = 6;

    public String generateCompanyCode(){
//        Todo : 코드 생성 로직 변경
//        1. CP접두사를 붙임.
//        2. 사업자등록번호를 시드로 랜덤 번호 6자리 완성
//        3. 완성된 회사코드 중복 검증
        return COM_PREFIX+generateRandomNumber();
    }

    public String generateDepartmentCode(){
//        Todo : 코드 생성 로직 변경
//        1. DP 주소의 영문 코드값 가져옴.
//        2. 사업자등록번호를 시드로 랜덤 번호 4자리 완성
//        3. 완성된 회사코드 중복 검증
        return DEP_PREFIX+generateRandomNumber();
    }

    private String generateRandomNumber() {
        StringBuilder number = new StringBuilder(MAX_LENGTH);
        Random random = new Random();
        for (int i = 0; i < MAX_LENGTH; i++) {
            number.append(random.nextInt(10)); // 0-9 사이의 랜덤 숫자 생성
        }
        return number.toString();
    }
}
