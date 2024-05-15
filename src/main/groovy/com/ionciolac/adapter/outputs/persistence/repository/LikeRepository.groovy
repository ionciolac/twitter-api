package com.ionciolac.adapter.outputs.persistence.repository

import com.ionciolac.adapter.outputs.persistence.entity.LikeEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface LikeRepository extends MongoRepository<LikeEntity, String> {

    List<LikeEntity> findAllByPostId(String id)

    Optional<LikeEntity> findByUserIdAndPostId(String userId, String postId)

    long countByPostId(String id)
}