package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.common.Role;
import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.dto.company.ManagerSignUpForm;
import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
import com.propertyservice.propertyservice.repository.company.*;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final ManagerRepository managerRepository;
    //private final ManagerAddressRepository managerAddressRepository;
    private final CompanyService companyService;
    //private final DepartmentService departmentService;
    private final AddressLevel1Repository addressLevel1Repository;
    private final AddressLevel2Respository addressLevel2Respository;
    private final DepartmentRepository departmentRepository;

    /**
     * 사용자 id를 통해 정보 가져오기
     * @param managerId : 사용자 ID
     * @return manager
     */
    public Long searchManagerById(Long managerId){
        return managerRepository.findByManagerId(managerId).orElseThrow(
                ()-> new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.\n관리자에게 문의하세요.")).getManagerId();

    }

    /**
     * 사용자 email를 통해 정보 가져오기
     * @param managerEmail : 사용자 email
     * @return manager
     */
    public Long searchManagerIdByEmail(String managerEmail){
        return managerRepository.findByManagerEmail(managerEmail).orElseThrow(
                ()-> new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.\n관리자에게 문의하세요.")).getManagerId();
    }
    /**
     * 사용자 email를 통해 정보 가져오기
     * @param managerEmail : 사용자 email
     * @return manager
     */
    public Manager searchManagerByEmail(String managerEmail){
        return managerRepository.findByManagerEmail(managerEmail).orElseThrow(
                ()-> new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.\n관리자에게 문의하세요."));
    }

    /**
     *  이메일 중복확인.
     * @param email : 사용자가 입력한 email
     * @return 중복시 false, 아니면 true
     */
    public boolean checkDuplicate(String email){
        try{
            Long manager = searchManagerIdByEmail(email);
            return false;
        }catch (Exception e){
            return true;
        }
    }

    /**
     * 사용자 회원가입.
     * @param managerSignUpForm
     * @return
     */
    @Transactional
    public Long createManager(ManagerSignUpForm managerSignUpForm) {
        log.info("departmentId : {}",managerSignUpForm.getDepartmentId());
        return managerRepository.save(Manager.builder()
                .company_id(companyService.searchCompany(managerSignUpForm.getCompanyCode()))
//                .department_id(departmentService.searchDepartment(managerSignUpForm.getDepartmentName()))
                .department_id(departmentRepository.findById(managerSignUpForm.getDepartmentId()).orElse(null))
                .managerName(managerSignUpForm.getManagerName())
                .managerRank(managerSignUpForm.getManagerRank())
                .managerPosition(managerSignUpForm.getManagerPosition())
                .managerCode(managerSignUpForm.getManagerCode())
                .managerState(managerSignUpForm.getManagerState())
                .gender(managerSignUpForm.getGender())
                .managerPhoneNumber(managerSignUpForm.getManagerPhoneNumber())
                .managerEntranceDate(LocalDateTime.now())
                .managerResignDate(LocalDateTime.now())
                .managerEmail(managerSignUpForm.getManagerEmail())
                .managerPassword(passwordEncoder.encode(managerSignUpForm.getManagerPassword()))
                .passwordErrorCount(0)
                .role(Role.COM_USER)
                .build()).getManagerId();
    }


    private Long validAddressLevel1(Long addressLevel1Id) {

        return addressLevel1Repository.findById(addressLevel1Id).orElseThrow(
                () -> new IOException("주소의 입력이 잘못되었습니다.")
        ).getAddressLevel1Id();
    }

    private Long validAddressLevel2(Long addressLevel2Id) {
        return addressLevel2Respository.findById(addressLevel2Id).orElseThrow(
                () -> new IOException("주소의 입력이 잘못되었습니다.")
        ).getAddressLevel2Id();
    }

    // SecurityContextHolder로 사용자 정보 가져오기.
    public UserDetails getCustomUserDetail(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Object principal = loggedInUser.getPrincipal();

//        if (principal instanceof CustomUserDetail) {
//            return (CustomUserDetail) principal;
//        } else {
//            throw new ClassCastException("Principal cannot be cast to CustomUserDetail");
//        }

        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        } else {
            throw new ClassCastException("Principal cannot be cast to CustomUserDetail");
        }
    }

    public String searchPassword(String managerEmail, String companyCode) {
        // 1. 회원 가져오기.
        Manager manager = searchManagerByEmail(managerEmail);

        // 2. 회사코드로 회사 가져오기.
        if (companyCode.equals(""))
            throw new IOException("회사코드가 입력되지 않았습니다.");
        Company company = companyService.searchCompany(companyCode);

        // 2. 회원의 회사코드가 일치하는지 확인.
//        if (!(manager.getCompany_id() == company))
//            throw new IllegalStateException("회사코드가 일치하지 않습니다.");

        // 3.비밀번호 재설정.
        System.out.println("current Password : " + manager.getManagerPassword());
        String password = manager.getManagerPassword().substring(5, 13);
        manager.resetPassword(passwordEncoder.encode(password));


        //4. 저장
        managerRepository.save(manager);

        return password;

    }

    public String resetPassword(String prePassword, String curPassword){
//        //1. 현재 로그인한 사용자 정보 가져옴.
//        CustomUserDetail customUserDetail = getCustomUserDetail();
//
//        // 2. 비밀번호 일치 여부 확인.
//        if(!customUserDetail.getPassword().equals(prePassword)){
//            throw new IllegalStateException("비밀번호가 일치하지 않거나 입력되지 않았습니다.");
//        }
//
//        // 3. 비밀번호 재설정.
//        Manager manager = searchManagerByEmail(customUserDetail.getUsername());
//        manager.resetPassword( passwordEncoder.encode(curPassword) );
//
//        managerRepository.save(manager);
//
//        return manager.getManagerPassword();

        //1. 현재 로그인한 사용자 정보 가져옴.
        UserDetails userDetails = getCustomUserDetail();

        // 2. 비밀번호 일치 여부 확인.
        if(!userDetails.getPassword().equals(prePassword)){
            throw new IllegalStateException("비밀번호가 일치하지 않거나 입력되지 않았습니다.");
        }

        // 3. 비밀번호 재설정.
        Manager manager = searchManagerByEmail(userDetails.getUsername());
        manager.resetPassword( passwordEncoder.encode(curPassword) );

        managerRepository.save(manager);

        return manager.getManagerPassword();

    }


    // 로그인.
    // security Login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("loadUserByUsername call");
        //System.out.println("managerEmail  " + username);
        Manager manager = searchManagerByEmail(username);

        //사용자 권한 USER로 설정.
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("COM_USER"));


        return new User(manager.getManagerEmail(), manager.getManagerPassword(), authorities);
        //return new CustomUserDetail(manager);
    }


}
