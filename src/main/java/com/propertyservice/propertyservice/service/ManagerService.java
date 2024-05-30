package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.domain.building.Owner;
import com.propertyservice.propertyservice.domain.company.Manager;
import com.propertyservice.propertyservice.domain.company.ManagerAddress;
import com.propertyservice.propertyservice.dto.company.ManagerForm;
import com.propertyservice.propertyservice.repository.common.AddressLevel1Repository;
import com.propertyservice.propertyservice.repository.common.AddressLevel2Respository;
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
     * 이메일 중복확인 validation
     * @param email 이메일
     * @return
     */
    private Manager validEmailDuplicate(String email) {
        return managerRepository.findByManagerEmail(email).orElse(null);
    }

    /**
     * 사용자 회원가입.
     * @param managerForm
     * @return
     */
    public Manager createManager(ManagerForm managerForm){
        ManagerAddress managerAddress = ManagerAddress.builder()
                .addressLevel1Id(validAddressLevel1(managerForm.getManagerAddressLevel1()))
                .addressLevel2Id(validAddressLevel2(managerForm.getManagerAddressLevel2()))
                .addressLevel3(managerForm.getManagerAddressLevel3())
                .build();

        return  managerRepository.save(Manager.builder()
                .managerName(managerForm.getManagerName())
                .managerPhoneNumber(managerForm.getManagerPhoneNumber())
                .managerAddressId(managerAddress)
                .gender(managerForm.getGender())
                .department_id(departmentService.searchDepartment(managerForm.getDepartmentName()))
                .managerRank(managerForm.getManagerRank())
                .managerPosition(managerForm.getManagerPosition())
                .managerState(managerForm.getState())
                .managerCode(managerForm.getManagerCode())
                .managerEmail(managerForm.getManagerEmail())
                .managerPassword(managerForm.getManagerPassword())
                .build());
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
