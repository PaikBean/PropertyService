package com.propertyservice.propertyservice.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

// security의 formLogin이 disable 되었기 때문에 따로 커스텀을 하기 위한 Filter

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final long VALID_TIME = 1000L * 60 * 60; // 1시간
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public LoginFilter(AuthenticationManager authenticationManager,
                       TokenProvider tokenProvider){
        log.info("LoginFilter construct");
        this.authenticationManager =authenticationManager;
        this.tokenProvider = tokenProvider;
        setFilterProcessesUrl("/api/manager/v1/login"); // login url setting
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("LoginFilter.attemptAuthentication() :  try to login");

        if(!request.getMethod().equals("POST")){
            log.warn("Request Method :" + request.getMethod());
            throw new AuthenticationServiceException("Authentication method not supported" + request.getMethod());
        }

        // 1. Username, password 받아오기
        String managerEmail = obtainUsername(request);
        String managerPassword = obtainPassword(request);
        log.info("managerEmail : " + managerEmail + "managerPassword : " + managerPassword);


        //2. Spring Security에서 username, password를 검증하기위해 token생성.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(managerEmail, managerPassword, null);

        //3. AuthenticationManager에게 전달
        return authenticationManager.authenticate(authToken);


    }
    
    // 인증 성공 메소드
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //System.out.println(authResult.getPrincipal().toString());
        User user = (User) authResult.getPrincipal();
        System.out.println(user.toString());

        // 2. 권한 가져오기.
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();

        Iterator<? extends  GrantedAuthority>  iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        //3. Token 생성 ( 이름, 권한, 만기일)
        String token = tokenProvider.generateJwtToken(user.getUsername(), role, VALID_TIME); // 1시간
        log.warn(token);
        response.addHeader("Authorization","Bearer " + token);
    }

    //인증 실패 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

    }
}
