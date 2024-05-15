package com.ionciolac.adapter.outputs.persistence.repository

import com.ionciolac.adapter.outputs.persistence.entity.FollowerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface FollowerRepository extends MongoRepository<FollowerEntity, String> {

    Page<FollowerEntity> findAllByFollowerId(String id, Pageable pageable)

    Optional<FollowerEntity> findByFollowerIdAndFollowingId(String followerId, String followingId)
}