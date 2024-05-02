package com.patrick.ohwoonwan.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/
@NoArgsConstructor
@AllArgsConstructor
public class CustomOauth2User implements OAuth2User {

    private String userId;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return this.userId;
    }
}
