package com.ionciolac.port.inputs

import com.ionciolac.domain.model.User

interface SecurityInPort {

    User getUserByUsername(String username)
}