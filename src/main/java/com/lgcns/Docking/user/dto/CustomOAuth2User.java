package com.lgcns.Docking.user.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;

    public CustomOAuth2User(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER"); // 기본 권한 디폴트 부여
    }

    @Override
    public String getName() {
        return userDTO.getName();
    }

    public String getPassword() {
        return userDTO.getUsername();
    }

    public String getId(){
        return userDTO.getId();
    }
}