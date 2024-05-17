package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectAlreadyExistException
import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.common.util.CommonMessage
import com.ionciolac.domain.model.User
import com.ionciolac.port.inputs.UserInPort
import com.ionciolac.port.outputs.UserOutPort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService implements UserInPort {
    UserOutPort userOutPort

    UserService(UserOutPort userOutPort) {
        this.userOutPort = userOutPort
    }

    @Override
    User createUser(User user) {
        Optional<User> userFromDB = userOutPort.getUser(user.getUsername(), user.getEmail(), user.getPhoneNumber())
        userFromDB.ifPresent { throw new ObjectAlreadyExistException(CommonMessage.USER_ALREADY_IS_REGISTERED) }
        user.setPassword(passwordEncoder().encode(user.getPassword()))
        user.setRole("CAN_ACCESS")
        return userOutPort.upsertUser(user)
    }

    @Override
    User updateUser(User user) {
        User dbUser = getDBUser(user.getId())
        dbUser.setUsername(user.getUsername())
        dbUser.setFirstName(user.getFirstName())
        dbUser.setLastName(user.getLastName())
        dbUser.setEmail(user.getEmail())
        dbUser.setPhoneNumber(user.getPhoneNumber())
        return userOutPort.upsertUser(dbUser)
    }

    @Override
    void deleteUser(String id) {
        userOutPort.deleteUser(id)
    }

    @Override
    User getUser(String id) {
        return getDBUser(id)
    }

    private def passwordEncoder() { return new BCryptPasswordEncoder() }

    private def getDBUser(String id) {
        def dbUser = userOutPort.getUser(id)
        if (dbUser.isPresent())
            return dbUser.get()
        else {
            String msg = String.format(CommonMessage.NOT_FOUND_MESSAGE, CommonMessage.USER, id)
            throw new ObjectNotFoundException(msg)
        }
    }
}
