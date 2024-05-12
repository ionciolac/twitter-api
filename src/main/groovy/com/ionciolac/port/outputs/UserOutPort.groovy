package com.ionciolac.port.outputs

import com.ionciolac.domain.model.User

interface UserOutPort {

    User upsertUser(User user)

    void deleteUser(String id)

    Optional<User> getUser(String id)

    Optional<User> getUser(String username, String email, String phoneNumber)

    Optional<User> getUserByUsername(String username)
}