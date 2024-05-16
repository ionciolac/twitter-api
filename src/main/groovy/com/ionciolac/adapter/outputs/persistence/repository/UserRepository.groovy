package com.ionciolac.adapter.outputs.persistence.repository

import com.ionciolac.adapter.outputs.persistence.entity.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber)

    Optional<UserEntity> findByUsername(String username)

    Page<UserEntity> findAllByFirstNameLike(String firstName, Pageable pageable)

    Page<UserEntity> findAllByLastNameLike(String lastName, Pageable pageable)

    Page<UserEntity> findAllByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable pageable)
}