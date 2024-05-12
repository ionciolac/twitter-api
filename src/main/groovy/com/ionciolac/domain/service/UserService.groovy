package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectAlreadyExistException
import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.domain.model.User
import com.ionciolac.port.inputs.UserInPort
import com.ionciolac.port.outputs.UserOutPort
import org.springframework.stereotype.Service

@Service
class UserService implements UserInPort {
    UserOutPort userOutPort

    UserService(UserOutPort userOutPort) {
        this.userOutPort = userOutPort
    }

    @Override
    User createUser(User user) {
        Optional<User> userFromDB = userOutPort.getUser(user.getLogin(), user.getEmail(), user.getPhoneNumber())
        userFromDB.ifPresent { throw new ObjectAlreadyExistException("User already exist in DB.") }
        return userOutPort.upsertUser(user)
    }

    @Override
    User updateUser(User user) {
        String userId = user.getId()
        Optional<User> userFromDB = userOutPort.getUser(userId)
        if (userFromDB.isPresent())
            return userOutPort.upsertUser(user)
        else
            throw new ObjectNotFoundException(String.format("User with id %s was not found in DB", userId))
    }

    @Override
    void deleteUser(String id) {
        userOutPort.deleteUser(id)
    }

    @Override
    User getUser(String id) {
        Optional<User> dbUser = userOutPort.getUser(id)
        if (dbUser.isPresent())
            return dbUser.get()
        else
            throw new ObjectNotFoundException(String.format("User with id %s was not found in DB", id))
    }
}
