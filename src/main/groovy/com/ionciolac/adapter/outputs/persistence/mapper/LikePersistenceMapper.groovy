package com.ionciolac.adapter.outputs.persistence.mapper

import com.ionciolac.adapter.outputs.persistence.entity.LikeEntity
import com.ionciolac.domain.model.Like

interface LikePersistenceMapper {

    Like toLike(LikeEntity likeEntity)

    LikeEntity toLikeEntity(Like like)
}