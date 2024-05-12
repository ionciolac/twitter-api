package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.req.user.CreateUserRequest
import com.ionciolac.adapter.inputs.rest.data.req.user.UserRequest
import com.ionciolac.adapter.inputs.rest.data.res.UserResponse
import com.ionciolac.domain.model.User

interface UserRestMapper {

    User toUser(CreateUserRequest createUserRequest)

    User toUser(UserRequest userRequest)

    UserResponse toUserResponse(User user)
}