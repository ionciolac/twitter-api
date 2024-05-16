package com.ionciolac.port.outputs

import com.ionciolac.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserOutPort {

    User upsertUser(User user)

    void deleteUser(String id)

    Optional<User> getUser(String id)

    Optional<User> getUser(String username, String email, String phoneNumber)

    Optional<User> getUserByUsername(String username)

    Page<User> searchUser(String firstName, String lastName, Pageable pageable)
}