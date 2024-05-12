package com.ionciolac.common.config.security

import com.ionciolac.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import java.util.stream.Collectors

class AuthenticatedUser {
    String id
    String username
    String password
    Collection<? extends GrantedAuthority> authorities

    private AuthenticatedUser(User user) {
        this.id = user.getId()
        this.username = user.getUsername()
        this.password = user.getPassword()
        this.authorities = user.getRole().toList().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE_".concat(role))
        }).collect(Collectors.toList())
    }

    static AuthenticatedUser create(User user) {
        return new AuthenticatedUser(user)
    }
}
