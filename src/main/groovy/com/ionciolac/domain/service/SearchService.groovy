package com.ionciolac.domain.service

import com.ionciolac.domain.model.User
import com.ionciolac.port.inputs.SearchInPort
import com.ionciolac.port.outputs.UserOutPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SearchService implements SearchInPort {

    UserOutPort userOutPort

    SearchService(UserOutPort userOutPort) {
        this.userOutPort = userOutPort
    }

    @Override
    Page<User> searchUser(String authorizedUserId, String firstName, String lastName, Pageable pageable) {
        return userOutPort.searchUser(firstName, lastName, pageable)
    }
}
