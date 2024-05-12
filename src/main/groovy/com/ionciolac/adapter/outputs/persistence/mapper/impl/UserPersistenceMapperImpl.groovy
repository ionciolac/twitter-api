package com.ionciolac.adapter.outputs.persistence.mapper.impl

import com.ionciolac.adapter.outputs.persistence.entity.UserEntity
import com.ionciolac.adapter.outputs.persistence.mapper.UserPersistenceMapper
import com.ionciolac.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceMapperImpl implements UserPersistenceMapper {
    @Override
    User toUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build()
    }

    @Override
    UserEntity toUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .build()
    }
}
