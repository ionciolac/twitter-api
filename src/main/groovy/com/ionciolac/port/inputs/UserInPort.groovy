package com.ionciolac.port.inputs

import com.ionciolac.domain.model.User

interface UserInPort {
    User createUser(User user)

    User updateUser(User user)

    void deleteUser(String id)

    User getUser(String id)
}