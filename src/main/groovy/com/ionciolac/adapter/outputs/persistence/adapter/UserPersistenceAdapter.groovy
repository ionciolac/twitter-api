package com.ionciolac.adapter.outputs.persistence.adapter

import com.ionciolac.adapter.outputs.persistence.entity.UserEntity
import com.ionciolac.adapter.outputs.persistence.mapper.UserPersistenceMapper
import com.ionciolac.adapter.outputs.persistence.repository.UserRepository
import com.ionciolac.domain.model.User
import com.ionciolac.port.outputs.UserOutPort
import org.springframework.stereotype.Service

@Service
class UserPersistenceAdapter implements UserOutPort {

    UserPersistenceMapper userPersistenceMapper
    UserRepository userRepository

    UserPersistenceAdapter(UserPersistenceMapper userPersistenceMapper, UserRepository userRepository) {
        this.userPersistenceMapper = userPersistenceMapper
        this.userRepository = userRepository
    }

    @Override
    User upsertUser(User user) {
        UserEntity userEntity = userPersistenceMapper.toUserEntity(user)
        userEntity = userRepository.save(userEntity)
        return userPersistenceMapper.toUser(userEntity)
    }

    @Override
    void deleteUser(String id) {
        userRepository.deleteById(id)
    }

    @Override
    Optional<User> getUser(String id) {
        Optional<UserEntity> dbUser = userRepository.findById(id)
        if (dbUser.isPresent())
            return Optional.ofNullable(userPersistenceMapper.toUser(dbUser.get()))
        else
            return Optional.empty()
    }

    @Override
    Optional<User> getUser(String login, String email, String phoneNumber) {
        Optional<UserEntity> dbUser = userRepository.findByLoginOrEmailOrPhoneNumber(login, email, phoneNumber)
        if (dbUser.isPresent())
            return Optional.ofNullable(userPersistenceMapper.toUser(dbUser.get()))
        else
            return Optional.empty()
    }
}
