package com.novomind.ecom.ichat.customisation.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.novomind.ecom.ichat.customisation.core.users.IChatUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails{
    private IChatUser user;

    public CustomUserDetails(IChatUser user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public String getId(){
        return user.getId();
    }

    public IChatUser getUser(){
        return this.user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
