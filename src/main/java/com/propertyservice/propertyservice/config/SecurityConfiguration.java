package com.propertyservice.propertyservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;  //실패 핸들러

    @Autowired
    private CustomAuthSueccessHandler customAuthSueccessHandler; //성공 핸들러.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin(form -> form
                .loginPage("/")                             //로그인 페이지
                .loginProcessingUrl("/")
                //.defaultSuccessUrl("/")                   //성공시 이동될 페이지
                .usernameParameter("email")                 //로그인시 id
                .passwordParameter("password")              //로그인시 password
                .failureHandler(customAuthFailureHandler)   //로그인 실패 핸들러
                .successHandler(customAuthSueccessHandler)  //로그인 성공 핸들러
        );
        http.logout(form -> form
                .logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/"))   //로그아웃시 다른 URL 재정의.
                .invalidateHttpSession(true) // 세션 초기화 (구현하다가 필요없음 제거할 예정)
        );

        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
