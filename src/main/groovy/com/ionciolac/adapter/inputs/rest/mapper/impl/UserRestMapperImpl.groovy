package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.req.user.CreateUserRequest
import com.ionciolac.adapter.inputs.rest.data.req.user.UpdateUserRequest
import com.ionciolac.adapter.inputs.rest.data.res.UserResponse
import com.ionciolac.adapter.inputs.rest.mapper.UserRestMapper
import com.ionciolac.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserRestMapperImpl implements UserRestMapper {

    @Override
    User toUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .login(createUserRequest.getLogin())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .password(createUserRequest.getPassword())
                .build()
    }

    @Override
    User toUser(UpdateUserRequest updateUserRequest) {
        return User.builder()
                .id(updateUserRequest.getId())
                .login(updateUserRequest.getLogin())
                .firstName(updateUserRequest.getFirstName())
                .lastName(updateUserRequest.getLastName())
                .email(updateUserRequest.getEmail())
                .phoneNumber(updateUserRequest.getPhoneNumber())
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
