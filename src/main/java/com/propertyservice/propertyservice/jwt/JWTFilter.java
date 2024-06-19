package com.propertyservice.propertyservice.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        log.info(authorization);

        // token이 null이거나 Bearer로 시작하지 않을 때.
        if(authorization == null || !authorization.startsWith("Bearer ")){
            log.warn("Token is null or wrong");
            filterChain.doFilter(request, response); //현재 filter 종료, 다음 필터로 넘겨줌.
            return;
        }

        String token = authorization.split(" ")[1];
        log.info("JWTFilter start");

        // 1. 소멸시간 검증
        if(tokenProvider.isExpired(token)){
            //만료
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        // 2. username, role 추출.
        String username = tokenProvider.getUsername(token);
        String role = tokenProvider.getRole(token);


        // 3. 정보 객체 담기
        User user = new User(username, "123123", Collections.singleton(new SimpleGrantedAuthority(role)));
        System.out.println(user.toString());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // 4. securityContextHolder에 등록. ( user 세션 생성.)
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // 5. 다음 필터 이동
        filterChain.doFilter(request, response);
    }
}
