package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.req.follower.FollowerRequest
import com.ionciolac.adapter.inputs.rest.data.res.FollowerResponse
import com.ionciolac.adapter.inputs.rest.mapper.FollowerRestMapper
import com.ionciolac.domain.model.Follower
import org.springframework.stereotype.Component

@Component
class FollowerRestMapperImpl implements FollowerRestMapper {

    @Override
    Follower toFollower(FollowerRequest followerRequest) {
        return Follower.builder()
                .followerId(followerRequest.getFollowerId())
                .followingId(followerRequest.getFollowingId())
                .build()
    }

    @Override
    FollowerResponse toFollowerResponse(Follower follower) {
        return FollowerResponse.builder()
                .id(follower.getId())
                .followerId(follower.getFollowerId())
                .followingId(follower.getFollowingId())
                .build()
    }
}
