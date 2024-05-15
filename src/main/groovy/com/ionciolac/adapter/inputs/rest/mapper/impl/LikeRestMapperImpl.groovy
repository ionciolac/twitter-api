package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.req.like.LikeRequest
import com.ionciolac.adapter.inputs.rest.data.res.LikeResponse
import com.ionciolac.adapter.inputs.rest.mapper.LikeRestMapper
import com.ionciolac.domain.model.Like
import org.springframework.stereotype.Component

@Component
class LikeRestMapperImpl implements LikeRestMapper {

    @Override
    Like toLike(LikeRequest likeRequest) {
        return Like.builder()
                .userId(likeRequest.getUserId())
                .postId(likeRequest.getPostId())
                .build()
    }

    @Override
    Like toLike(LikeResponse likeResponse) {
        return Like.builder()
                .id(likeResponse.getId())
                .userId(likeResponse.getUserId())
                .postId(likeResponse.getPostId())
                .build()
    }

    @Override
    LikeResponse toLikeResponse(Like like) {
        return LikeResponse.builder()
                .id(like.getId())
                .userId(like.getUserId())
                .postId(like.getPostId())
                .build()
    }
}
