package com.ionciolac.adapter.outputs.persistence.mapper.impl

import com.ionciolac.adapter.outputs.persistence.entity.FollowerEntity
import com.ionciolac.adapter.outputs.persistence.mapper.FollowerPersistenceMapper
import com.ionciolac.domain.model.Follower
import org.springframework.stereotype.Component

@Component
class FollowerPersistenceMapperImpl implements FollowerPersistenceMapper {

    @Override
    Follower toFollower(FollowerEntity followerEntity) {
        return Follower.builder()
                .id(followerEntity.getId())
                .followerId(followerEntity.getFollowerId())
                .followingId(followerEntity.getFollowingId())
                .build()
    }

    @Override
    FollowerEntity toFollowerEntity(Follower follower) {
        return FollowerEntity.builder()
                .id(follower.getId())
                .followerId(follower.getFollowerId())
                .followingId(follower.getFollowingId())
                .build()
    }
}
