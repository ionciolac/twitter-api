package com.ionciolac.adapter.outputs.persistence.mapper.impl

import com.ionciolac.adapter.outputs.persistence.entity.UserEntity
import com.ionciolac.adapter.outputs.persistence.mapper.UserPersistenceMapper
import com.ionciolac.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceMapperImpl implements UserPersistenceMapper {

    @Override
    User toUser(UserEntity userEntity) {
        User user = new User()
        user.setId(userEntity.getId())
        user.setLogin(userEntity.getLogin())
        user.setFirstName(userEntity.getFirstName())
        user.setLastName(userEntity.getLastName())
        user.setEmail(userEntity.getEmail())
        user.setPhoneNumber(userEntity.getPhoneNumber())
        user.setCreatedOn(userEntity.getCreatedOn())
        user.setUpdatedOn(userEntity.getUpdatedOn())
        return user
    }

    @Override
    UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity()
        userEntity.setId(user.getId())
        userEntity.setLogin(user.getLogin())
        userEntity.setFirstName(user.getFirstName())
        userEntity.setLastName(user.getLastName())
        userEntity.setEmail(user.getEmail())
        userEntity.setPhoneNumber(user.getPhoneNumber())
        userEntity.setPassword(user.getPassword())
        userEntity.setCreatedOn(user.getCreatedOn())
        userEntity.setUpdatedOn(user.getUpdatedOn())
        return userEntity
    }
}
