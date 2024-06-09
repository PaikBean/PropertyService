package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.company.Manager;
import com.propertyservice.propertyservice.domain.company.ManagerAddress;
import com.propertyservice.propertyservice.dto.company.ManagerSignUpForm;
import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
import com.propertyservice.propertyservice.repository.company.CompanyRepository;
import com.propertyservice.propertyservice.repository.company.DepartmentRepository;
import com.propertyservice.propertyservice.repository.company.ManagerAddressRepository;
import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ManagerAddressRepository managerAddressRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AddressLevel1Repository addressLevel1Repository;
    @Autowired
    private AddressLevel2Respository addressLevel2Respository;

    /**
     * 사용자 id를 통해 정보 가져오기
     * @param managerId : 사용자 ID
     * @return manager
     */
    public Manager searchManagerById(Long managerId){
        return managerRepository.findByManagerId(managerId).orElseThrow(
                ()-> new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.\n관리자에게 문의하세요."));

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
            Manager manager = searchManagerByEmail(email);
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
        return managerRepository.save(Manager.builder()
                .company_id(companyRepository.findByCompanyCode(managerSignUpForm.getCompanyCode()).orElseThrow(
                        () -> new EntityNotFoundException("회사 코드 오류!")))
                .department_id(departmentRepository.findByDepartmentName(managerSignUpForm.getDepartmentName()).orElseThrow(
                        () -> new EntityNotFoundException("부서 이름 오류!")))
                .managerName(managerSignUpForm.getManagerName())
                .managerRank(managerSignUpForm.getManagerRank())
                .managerPosition(managerSignUpForm.getManagerPosition())
                .managerCode(managerSignUpForm.getManagerCode())
                .managerState(managerSignUpForm.getState())
                .gender(managerSignUpForm.getGender())
                .managerPhoneNumber(managerSignUpForm.getManagerPhoneNumber())
                .managerAddressId(managerAddressRepository.save(ManagerAddress.builder()
                        .addressLevel1Id(validAddressLevel1(managerSignUpForm.getManagerAddressLevel1()))
                        .addressLevel2Id(validAddressLevel2(managerSignUpForm.getManagerAddressLevel2()))
                        .addressLevel3(managerSignUpForm.getManagerAddressLevel3())
                        .build()))
                .managerEntranceDate(LocalDateTime.now())
                .managerResignDate(LocalDateTime.now())
                .managerEmail(managerSignUpForm.getManagerEmail())
                .managerPassword(passwordEncoder.encode(managerSignUpForm.getManagerPassword()))
                .passwordErrorCount(0)
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
    @Override
    public UserDetails loadUserByUsername(String managerEmail) throws UsernameNotFoundException {
        Manager manager = searchManagerByEmail(managerEmail);

        //사용자 권한 USER로 설정.
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new User(manager.getManagerEmail(), manager.getManagerPassword(), authorities);
    }
}
