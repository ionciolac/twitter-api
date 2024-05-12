package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectAlreadyExistException
import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.domain.model.User
import com.ionciolac.port.inputs.SecurityInPort
import com.ionciolac.port.inputs.UserInPort
import com.ionciolac.port.outputs.UserOutPort
import org.springframework.beans.BeanUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService implements UserInPort, SecurityInPort {
    UserOutPort userOutPort

    UserService(UserOutPort userOutPort) {
        this.userOutPort = userOutPort
    }

    @Override
    User createUser(User user) {
        Optional<User> userFromDB = userOutPort.getUser(user.getUsername(), user.getEmail(), user.getPhoneNumber())
        userFromDB.ifPresent { throw new ObjectAlreadyExistException("User already exist in DB.") }
        user.setPassword(passwordEncoder().encode(user.getPassword()))
        user.setRole("CAN_ACCESS")
        return userOutPort.upsertUser(user)
    }

    @Override
    User updateUser(User user) {
        String userId = user.getId()
        Optional<User> userFromDB = userOutPort.getUser(userId)
        if (userFromDB.isPresent()) {
            User dbUser = userFromDB.get()
            BeanUtils.copyProperties(user, dbUser, "createdOn", "password", "role")
            return userOutPort.upsertUser(dbUser)
        } else
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

    @Override
    User getUserByUsername(String username) {
        Optional<User> dbUser = userOutPort.getUserByUsername(username)
        if (dbUser.isPresent())
            return dbUser.get()
        else
            throw new ObjectNotFoundException(String.format("User with username %s was not found in DB", username))
    }

    def passwordEncoder() {
        return new BCryptPasswordEncoder()
    }
}
