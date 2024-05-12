package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.req.user.CreateUserRequest
import com.ionciolac.adapter.inputs.rest.data.req.user.UpdateUserRequest
import com.ionciolac.adapter.inputs.rest.data.res.UserResponse
import com.ionciolac.domain.model.User

interface UserRestMapper {

    User toUser(CreateUserRequest createUserRequest)

    User toUser(UpdateUserRequest updateUserRequest)

    UserResponse toUserResponse(User user)
}