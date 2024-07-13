package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.common.Role;
import com.propertyservice.propertyservice.domain.manager.Manager;
import com.propertyservice.propertyservice.domain.manager.ManagerState;
import com.propertyservice.propertyservice.dto.company.CustomUserDetail;
import com.propertyservice.propertyservice.dto.company.ManagerSignUpForm;
import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
import com.propertyservice.propertyservice.repository.common.ManagerStateRepository;
import com.propertyservice.propertyservice.repository.company.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final ManagerRepository managerRepository;
    private final ManagerAddressRepository managerAddressRepository;
    private final ManagerStateRepository managerStateRepository;
    private final CompanyService companyService;
    private final DepartmentService departmentService;
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
    public Long searchManagerByEmail(String managerEmail){
        return managerRepository.findByManagerEmail(managerEmail).orElseThrow(
                ()-> new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.\n관리자에게 문의하세요.")).getManagerId();
    }

    /**
     *  이메일 중복확인.
     * @param email : 사용자가 입력한 email
     * @return 중복시 false, 아니면 true
     */
    public boolean checkDuplicate(String email){
        try{
            Long manager = searchManagerByEmail(email);
            return false;
        }catch (Exception e){
            return true;
        }
    }

    public ManagerState searchStateById(Long managerStateId){
        return managerStateRepository.findByManagerStateId(managerStateId).orElseThrow(
                () -> new EntityNotFoundException("State Id 값 오류.")
        );
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
                .managerStateId(searchStateById(managerSignUpForm.getManagerStateId()))
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
                () -> new IllegalStateException("주소의 입력이 잘못되었습니다.")
        ).getAddressLevel1Id();
    }

    private Long validAddressLevel2(Long addressLevel2Id) {
        return addressLevel2Respository.findById(addressLevel2Id).orElseThrow(
                () -> new IllegalStateException("주소의 입력이 잘못되었습니다.")
        ).getAddressLevel2Id();
    }


    // 로그인.
    // security Login
    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("managerEmail  " + username);
        Manager manager = managerRepository.findByManagerEmail(username).orElseThrow(
                () -> new EntityNotFoundException("사용자 정보가 존재하지 않습니다. /n 회원가입 후 이용해주세요.")
        );
        //사용자 권한 USER로 설정.
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("COM_USER"));


        //return new User(manager.getManagerEmail(), manager.getManagerPassword(), authorities);
        return  new CustomUserDetail(manager);
    }

}
