package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.req.user.CreateUserRequest
import com.ionciolac.adapter.inputs.rest.data.req.user.UserRequest
import com.ionciolac.adapter.inputs.rest.data.res.UserResponse
import com.ionciolac.adapter.inputs.rest.mapper.UserRestMapper
import com.ionciolac.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserRestMapperImpl implements UserRestMapper {

    @Override
    User toUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .password(createUserRequest.getPassword())
                .build()
    }

    @Override
    User toUser(UserRequest userRequest) {
        return User.builder()
                .username(userRequest.getUsername())
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
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build()
    }
}
