package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Manager;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ManagerService implements UserDetailsService {

    @Autowired
    private ManagerRepository managerRepository;

    /*
        사용자 정보 가져오기
        @managerId : 번호
    */
    public Manager searchManagerById(Long managerId){
        return managerRepository.findByManagerId(managerId).orElseThrow(
                ()-> new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.\n관리자에게 문의하세요."));

    }
    /*
        사용자 정보 가져오기
        @managerEmail : 이메일
    */
    public Manager searchManagerByEmail(String managerEmail){
        return managerRepository.findByManagerEmail(managerEmail).orElseThrow(
                ()-> new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.\n관리자에게 문의하세요."));
    }

    /*
        회원가입시 email 중복 체크
        @ false : 이메일 중복
        @ true : 생성 가능.
     */
    public boolean checkDuplicate(String email){
        try{
            Manager manager = searchManagerByEmail(email);
            return false;
        }catch (Exception e){
            return true;
        }
    }



    /*
        사용자 로그인
    */
    @Override
    public UserDetails loadUserByUsername(String managerEmail) throws UsernameNotFoundException {
        Manager manager = searchManagerByEmail(managerEmail);

        //사용자 권한 USER로 설정.
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new User(manager.getManagerEmail(), manager.getManagerPassword(), authorities);
    }
}
