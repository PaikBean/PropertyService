package com.propertyservice.propertyservice.config;

import com.propertyservice.propertyservice.jwt.LoginFilter;
import com.propertyservice.propertyservice.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    //private final CustomAuthFailureHandler customAuthFailureHandler;  //실패 핸들러
    //private final CustomAuthSueccessHandler customAuthSueccessHandler; //성공 핸들러.
    private final AuthenticationConfiguration authenticationConfiguration;
    private final TokenProvider tokenProvider;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // session을  stateless 상태로 관리 하기 때문에 csrf를 disable 해줌.
        http.csrf(AbstractHttpConfigurer::disable);

        //jwt 방식은 formLogin과 httpBasic을 사용하지 않음.
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        // 경로별 인가 작업.
        // 현재는 모든 사이트 접근을 허용.
        http.authorizeHttpRequests((auth) -> auth
                //.requestMatchers("/login", "/sign-up", "/main").permitAll()
                .anyRequest().permitAll());

        // usernamePasswordAuthenticationFilter 등록.
        // addFilterAt : 정해진 위치에 필터를 등록.
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), tokenProvider), UsernamePasswordAuthenticationFilter.class);

        // 세션 설정.
        // stateless : http와 같은 client의 이전 상태를 관리하지 않음.
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();
    }


    // JWT를 사용하지 않았을 때.
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable);

        http.formLogin(form -> form
                .loginPage("/")                             //로그인 페이지
                .loginProcessingUrl("/")
                //.defaultSuccessUrl("/")                   //성공시 이동될 페이지
                .usernameParameter("managerEmail")                 //로그인시 id
                .passwordParameter("managerPassword")              //로그인시 password
                .failureHandler(customAuthFailureHandler)   //로그인 실패 핸들러
                .successHandler(customAuthSueccessHandler)  //로그인 성공 핸들러
        );
        http.logout(form -> form
                .logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/"))   //로그아웃시 다른 URL 재정의.
                .invalidateHttpSession(true) // 세션 초기화 (구현하다가 필요없음 제거할 예정)
        );

        return http.build();
    }
     */
}
