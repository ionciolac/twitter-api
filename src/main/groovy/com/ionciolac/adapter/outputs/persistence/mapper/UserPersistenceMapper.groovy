package com.ionciolac.adapter.outputs.persistence.mapper

import com.ionciolac.adapter.outputs.persistence.entity.UserEntity
import com.ionciolac.domain.model.User

interface UserPersistenceMapper {

    User toUser(UserEntity userEntity)

    UserEntity toUserEntity(User user)
}
