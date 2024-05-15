package com.ionciolac.adapter.outputs.persistence.mapper.impl

import com.ionciolac.adapter.outputs.persistence.entity.LikeEntity
import com.ionciolac.adapter.outputs.persistence.mapper.LikePersistenceMapper
import com.ionciolac.domain.model.Like
import org.springframework.stereotype.Component

@Component
class LikePersistenceMapperImpl implements LikePersistenceMapper {

    @Override
    Like toLike(LikeEntity likeEntity) {
        return Like.builder()
                .id(likeEntity.getId())
                .userId(likeEntity.getUserId())
                .postId(likeEntity.getPostId())
                .build()
    }

    @Override
    LikeEntity toLikeEntity(Like like) {
        return LikeEntity.builder()
                .id(like.getId())
                .userId(like.getUserId())
                .postId(like.getPostId())
                .build()
    }
}
