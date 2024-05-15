package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.common.util.CommonMessage
import com.ionciolac.domain.model.User
import com.ionciolac.port.inputs.SecurityInPort
import com.ionciolac.port.outputs.UserOutPort
import org.springframework.stereotype.Service

@Service
class SecurityService implements SecurityInPort {

    UserOutPort userOutPort

    SecurityService(UserOutPort userOutPort) {
        this.userOutPort = userOutPort
    }

    @Override
    User getUserByUsername(String username) {
        Optional<User> dbUser = userOutPort.getUserByUsername(username)
        if (dbUser.isPresent())
            return dbUser.get()
        else
            throw new ObjectNotFoundException(String.format(CommonMessage.USER_WAS_NOT_FOUND_BY_USERNAME, username))
    }
}
