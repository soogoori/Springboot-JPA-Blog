package com.lookie.demo.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import com.lookie.demo.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.Data;

// Authentication 객체에 저장할 수 있는 유일한 타입
@Data
public class PrincipalDetail implements UserDetails{

    private User user;

    public PrincipalDetail(User user) {
        super();
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
        collet.add(()->{ return "ROLE_"+user.getRole();});
        return collet;
    }



}