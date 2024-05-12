package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.req.CreateUserRequest
import com.ionciolac.adapter.inputs.rest.data.req.UpdateUserRequest
import com.ionciolac.adapter.inputs.rest.data.res.UserResponse
import com.ionciolac.domain.model.User

interface UserRestMapper {

    User toUser(CreateUserRequest userRequest)

    User toUser(UpdateUserRequest userRequest)

    UserResponse toUserResponse(User user)
}