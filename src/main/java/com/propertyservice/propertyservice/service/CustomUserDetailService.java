package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.dto.manager.CustomUserDetail;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    // 로그인.
    // security Login

    private final ManagerRepository managerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("loadUserByUsername call");
        //System.out.println("managerEmail  " + username);
        Manager manager = managerRepository.findByManagerEmail(username).orElseThrow(
                () -> new EntityNotFoundException("이메일이 틀렸거나 존재하지 않는 회원입니다.")
        );

        //사용자 권한 USER로 설정.
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("COM_USER"));


        //return new User(manager.getManagerEmail(), manager.getManagerPassword(), authorities);
        return new CustomUserDetail(manager);
    }
}
