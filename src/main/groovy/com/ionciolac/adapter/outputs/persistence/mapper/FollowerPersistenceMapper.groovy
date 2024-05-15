package com.ionciolac.adapter.outputs.persistence.mapper

import com.ionciolac.adapter.outputs.persistence.entity.FollowerEntity
import com.ionciolac.domain.model.Follower

interface FollowerPersistenceMapper {

    Follower toFollower(FollowerEntity followerEntity)

    FollowerEntity toFollowerEntity(Follower follower)
}