package com.ionciolac.common.config.security

import com.ionciolac.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class AuthenticatedUser {
    String id
    String username
    String password
    Collection<? extends GrantedAuthority> authorities

    private AuthenticatedUser(User user) {
        this.id = user.getId()
        this.username = user.getUsername()
        this.password = user.getPassword()
        this.authorities = getAuthorities(user)
    }

    static def create(User user) {
        return new AuthenticatedUser(user)
    }

    def getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>()
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())
        authorities.add(authority)
        return authorities
    }
}
