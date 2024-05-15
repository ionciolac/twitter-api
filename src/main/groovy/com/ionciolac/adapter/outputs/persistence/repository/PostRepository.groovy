package com.ionciolac.adapter.outputs.persistence.repository

import com.ionciolac.adapter.outputs.persistence.entity.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository extends MongoRepository<PostEntity, String> {

    Page<PostEntity> findAllByUserId(String userId, Pageable pageable)

    Page<PostEntity> findAllByUserIdIn(List<String> userId, Pageable pageable)
}