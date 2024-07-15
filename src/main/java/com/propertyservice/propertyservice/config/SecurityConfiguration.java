package com.propertyservice.propertyservice.config;

import com.propertyservice.propertyservice.dto.company.CustomUserDetail;
import com.propertyservice.propertyservice.jwt.JWTFilter;
import com.propertyservice.propertyservice.jwt.LoginFilter;
import com.propertyservice.propertyservice.jwt.TokenProvider;
import com.propertyservice.propertyservice.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

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
                .requestMatchers("/admin").hasRole("COM_USER")
                .anyRequest().permitAll());

        // usernamePasswordAuthenticationFilter 등록.
        // addFilterAt : 정해진 위치에 필터를 등록.
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), tokenProvider), UsernamePasswordAuthenticationFilter.class);

        //jwtfilter 추가.
        http.addFilterBefore(new JWTFilter(tokenProvider), LoginFilter.class);

        // 세션 설정.
        // stateless : http와 같은 client의 이전 상태를 관리하지 않음.
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        // cors 설정.
        http.cors((cors) -> cors
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        // 1. Cors설정 객체 생성.
                        CorsConfiguration configuration = new CorsConfiguration();

                        // 2. 설정 등록.
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // 포트 허용
                        configuration.setAllowedMethods(Collections.singletonList("*")); // get,post 등등 모든 메소드 허용
                        configuration.setAllowCredentials(true); // Credentials 사용하면 허용.
                        configuration.setAllowedHeaders(Collections.singletonList("*")); // 헤더 허용.
                        configuration.setMaxAge(3600L);
                        configuration.setExposedHeaders(Collections.singletonList("Authorization")); // 백엔드에서 프론트로 토큰 값 보내는 것을 허용.

                        return configuration;
                    }
                }));

        return http.build();
    }

}
