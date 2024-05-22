package com.propertyservice.propertyservice.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    // 로그인 실패시 아래 errorMessage를 보냄.
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        if(exception instanceof UsernameNotFoundException){
            errorMessage = "계정이 존재하지 않습니다.\n회원가입 진행 후 로그인 해주세요.";
        }
        else if(exception instanceof BadCredentialsException){
            errorMessage = "아이디 혹은 비밀번호가 맞지 않습니다.\n다시 확인해 주세요.";
        }
        else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. \n관리자에게 문의하세요.";
        }
        else if(exception instanceof AuthenticationCredentialsNotFoundException){
            errorMessage = "인증 요청이 거부되었습니다. \n관리자에게 문의하세요.";
        }
        else{
            errorMessage = "알 수 없는 오류로 로그인을 실패하였습니다. \n관리자에게 문의하세요.";
        }

        // 1. url로 errormessage 보내기
        String encodingErrorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        //setDefaultFailureUrl("실패시 이동될 URL" + encodingErrorMessage);


        super.onAuthenticationFailure(request, response, exception);
    }
}
