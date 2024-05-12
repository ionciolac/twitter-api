package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.req.CreateUserRequest
import com.ionciolac.adapter.inputs.rest.data.req.UpdateUserRequest
import com.ionciolac.adapter.inputs.rest.data.res.UserResponse
import com.ionciolac.adapter.inputs.rest.mapper.UserRestMapper
import com.ionciolac.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserRestMapperImpl implements UserRestMapper {

    @Override
    User toUser(CreateUserRequest userRequest) {
        return User.builder()
                .login(userRequest.getLogin())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .build()
    }

    @Override
    User toUser(UpdateUserRequest userRequest) {
        return User.builder()
                .id(userRequest.getId())
                .login(userRequest.getLogin())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .build()
    }

    @Override
    UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build()
    }
}
