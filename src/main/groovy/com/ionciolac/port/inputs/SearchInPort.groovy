package com.ionciolac.port.inputs

import com.ionciolac.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SearchInPort {

    Page<User> searchUser(String authorizedUserId, String firstName, String lastName, Pageable pageable)
}