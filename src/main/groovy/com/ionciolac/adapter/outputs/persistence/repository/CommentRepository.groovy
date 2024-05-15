package com.ionciolac.adapter.outputs.persistence.repository

import com.ionciolac.adapter.outputs.persistence.entity.CommentEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository extends MongoRepository<CommentEntity, String> {

    Page<CommentEntity> findAllByPostId(String id, Pageable pageable)

    long countByPostId(String id)
}