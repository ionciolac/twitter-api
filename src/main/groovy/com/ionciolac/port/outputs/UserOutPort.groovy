package com.ionciolac.port.outputs

import com.ionciolac.domain.model.User

interface UserOutPort {

    User upsertUser(User user)

    void deleteUser(String id)

    Optional<User> getUser(String id)

    Optional<User> getUser(String login, String email, String phoneNumber)
}