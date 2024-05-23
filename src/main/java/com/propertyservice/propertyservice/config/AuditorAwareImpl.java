package com.propertyservice.propertyservice.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // 현재 로그인한 사용자 정보를 저장.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        String manage_email = "";
        if(authentication != null)
            manage_email = authentication.getName();

        return Optional.of(manage_email); // mamage_email null이 아닌 경우 사용.
    }
}
