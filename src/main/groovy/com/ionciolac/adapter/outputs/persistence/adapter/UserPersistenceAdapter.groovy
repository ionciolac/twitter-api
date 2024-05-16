package com.ionciolac.adapter.outputs.persistence.adapter


import com.ionciolac.adapter.outputs.persistence.mapper.UserPersistenceMapper
import com.ionciolac.adapter.outputs.persistence.repository.UserRepository
import com.ionciolac.domain.model.User
import com.ionciolac.port.outputs.UserOutPort
import org.apache.commons.lang3.StringUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
        def userEntity = userPersistenceMapper.toUserEntity(user)
        userEntity = userRepository.save(userEntity)
        return userPersistenceMapper.toUser(userEntity)
    }

    @Override
    void deleteUser(String id) {
        userRepository.deleteById(id)
    }

    @Override
    Optional<User> getUser(String id) {
        return userRepository.findById(id).map { userPersistenceMapper.toUser(it) }
    }

    @Override
    Optional<User> getUser(String username, String email, String phoneNumber) {
        userRepository.findByUsernameOrEmailOrPhoneNumber(username, email, phoneNumber)
                .map { userPersistenceMapper.toUser(it) }
    }

    @Override
    Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map { userPersistenceMapper.toUser(it) }
    }

    @Override
    Page<User> searchUser(String firstName, String lastName, Pageable pageable) {
        if (StringUtils.isNoneEmpty(firstName) && StringUtils.isEmpty(lastName)) {
            return userRepository.findAllByFirstNameLike(firstName, pageable)
                    .map { userPersistenceMapper.toUser(it) }
        }
        if (StringUtils.isEmpty(firstName) && StringUtils.isNoneEmpty(lastName)) {
            return userRepository.findAllByLastNameLike(lastName, pageable)
                    .map { userPersistenceMapper.toUser(it) }
        }
        return userRepository.findAllByFirstNameLikeAndLastNameLike(firstName, lastName, pageable)
                .map { userPersistenceMapper.toUser(it) }
    }
}
