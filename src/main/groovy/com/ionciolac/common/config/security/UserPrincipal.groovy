package com.ionciolac.common.config.security

import com.ionciolac.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import java.util.stream.Collectors

class UserPrincipal {
    String username
    String password
    Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(User user) {
        this.username = user.getUsername()
        this.password = user.getPassword()
        this.authorities = user.getRole().toList().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE_".concat(role))
        }).collect(Collectors.toList())
    }

    static UserPrincipal create(User user) {
        return new UserPrincipal(user)
    }
}
