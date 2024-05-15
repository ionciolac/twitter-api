package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.req.follower.FollowerRequest
import com.ionciolac.adapter.inputs.rest.data.res.FollowerResponse
import com.ionciolac.domain.model.Follower

interface FollowerRestMapper {

    Follower toFollower(FollowerRequest followerRequest)

    FollowerResponse toFollowerResponse(Follower follower)
}