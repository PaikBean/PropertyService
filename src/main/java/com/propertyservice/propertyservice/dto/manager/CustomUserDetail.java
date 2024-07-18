package com.propertyservice.propertyservice.dto.manager;

import com.propertyservice.propertyservice.domain.company.Company;
import com.propertyservice.propertyservice.domain.company.Department;
import com.propertyservice.propertyservice.domain.manager.Manager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetail implements UserDetails {
    private final Manager manager;

    public CustomUserDetail(Manager manager){
        this.manager = manager;
    }

    public Company getCompany(){
        return manager.getCompany_id();
    }
    public Department getDepartment(){
        return manager.getDepartment_id();
    }
    public String getManagerName(){
        return manager.getManagerName();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(manager.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return manager.getManagerPassword();
    }

    @Override
    public String getUsername() {
        return manager.getManagerEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
