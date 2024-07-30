package com.propertyservice.propertyservice.controller;

import com.propertyservice.propertyservice.domain.common.Response;
import com.propertyservice.propertyservice.domain.common.ResponseCode;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.dto.manager.ManagerSignUpForm;
import com.propertyservice.propertyservice.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/manager/")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;


    //이메일 중복 조회
    //버튼으로 클릭조회
    @GetMapping("/v1/duplicate")
    public Response checkEmail(@RequestParam(value = "email", defaultValue = "")String email){
        if(managerService.checkDuplicate(email))
            return new Response(ResponseCode.SUCCESS, false,"200"); // 이메일 사용가능
        else
            return new Response(ResponseCode.SUCCESS, true, "201"); // 이메일 사용불가.
    }
    // 이메일 중복 조회
    // onChanged 사용.
    @GetMapping("/v1/check-email")
    public Response checkChange(@RequestParam(value = "email", defaultValue = "")String email){
        // 에러가 발생해야 중복이 없다는 뜻.
        try {
            return new Response(ResponseCode.SUCCESS, true,"200"); // 이메일 사용불가
        }catch (Exception e){
            return new Response(ResponseCode.SUCCESS, false,"200"); // 이메일 사용가능
        }

    }

    // 회원가입
    @PostMapping("/v1/sign-up")
    public Response createManager(@RequestBody ManagerSignUpForm managerSignUpForm){
        try{
            return new Response(ResponseCode.SUCCESS, managerService.createManager(managerSignUpForm),"201");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

//    /**
//     * 로그인.  // 자동으로 filter가 가로채기 때문에 서비스를 호출할 필요가 없음.
//     * @param loginFormDto
//     * @return
//     */
//    @PostMapping("/v1/login")
//    public Response login(@RequestBody LoginFormDto loginFormDto){
//        log.info("로그인 시도");
//        try{
//            return new Response(ResponseCode.SUCCESS, managerService ,"200");
//        }catch (Exception e){
//            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
//        }
//    }

    /**
     * 비밀번호 찾기.
     * @return
     */
    @GetMapping("/v1/manager-password")
    public Response searchPassword(@RequestParam(value = "managerEmail", defaultValue = "")String managerEmail,
                                  @RequestParam(value = "companyCode", defaultValue = "")String companyCode){
        log.info("Password 찾기" );
        log.info("managerEmail : " + managerEmail + "companyCode : "+ companyCode);
        try{
            return new Response(ResponseCode.SUCCESS, managerService.searchPassword(managerEmail, companyCode),"200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     * 비밀번호 재설정.
     * @return
     */
    @PutMapping("/v1/manager-password-reset")
    public Response resetPassword(@RequestParam(value = "prePassword", defaultValue = "")String prePassword,
                                  @RequestParam(value = "curPassword", defaultValue = "")String curPassword){
        try{
            return new Response(ResponseCode.SUCCESS, managerService.resetPassword(prePassword, curPassword),"200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "401");
        }
    }


    @GetMapping("/v1/manager-list/{companyId}")
    public Response searchMangerList(@PathVariable("companyId")Long companyId){
        try{
            List<Manager> managerList = managerService.searchManagerList(companyId);
            return managerList.isEmpty()
                    ? new Response(ResponseCode.SUCCESS, managerList, "204")
                    : new Response(ResponseCode.SUCCESS, managerList, "200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "400");
        }
    }

    /**
     *  SecurityContentHolder 마이페이지 회원 정보 조회.
     * @return
     */
    @GetMapping("/v1/my-info")
    public Response searchManagerInfo(){
        try{
            return new Response(ResponseCode.SUCCESS, managerService.searchManagerInfo(),"200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "401");
        }
    }
    /**
     *  managerId 마이페이지 회원 정보 조회.
     * @return
     */
    @GetMapping("/v1/my-info/{managerId}")
    public Response searchManagerInfo(@PathVariable("managerId")Long managerId){
        try{
            return new Response(ResponseCode.SUCCESS, managerService.searchManagerInfo(managerId),"200");
        }catch (Exception e){
            return new Response(ResponseCode.FAIL, e.getMessage(), "401");
        }
    }

}
