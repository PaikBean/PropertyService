package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.dto.company.ManagerSignUpForm;
import com.propertyservice.propertyservice.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/manager/v1/")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;


    //이메일 중복 조회
    //버튼으로 클릭조회
    @GetMapping("/duplicate")
    public Response checkEmail(@RequestParam(value = "email", defaultValue = "")String email){
        if(managerService.checkDuplicate(email))
            return new Response(ResponseCode.SUCCESS, false,"200"); // 이메일 사용가능
        else
            return new Response(ResponseCode.SUCCESS, true, "200"); // 이메일 사용불가.
    }
    // 이메일 중복 조회
    // onChanged 사용.
    @GetMapping("/check-email")
    public Response checkChange(@RequestParam(value = "email", defaultValue = "")String email){
        // 에러가 발생해야 중복이 없다는 뜻.
        try {
            return new Response(ResponseCode.SUCCESS, true,"200"); // 이메일 사용불가
        }catch (Exception e){
            return new Response(ResponseCode.SUCCESS, false,"200"); // 이메일 사용가능
        }

    }

    // 회원가입
    @PostMapping("/sign-up")
    public Response createManager(ManagerSignUpForm managerSignUpForm){
        try{
            return new Response(ResponseCode.SUCCESS, managerService.createManager(managerSignUpForm),"201");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }
}
