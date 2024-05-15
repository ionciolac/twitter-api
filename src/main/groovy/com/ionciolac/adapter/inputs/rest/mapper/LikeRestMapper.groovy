package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.req.like.LikeRequest
import com.ionciolac.adapter.inputs.rest.data.res.LikeResponse
import com.ionciolac.domain.model.Like

interface LikeRestMapper {

    Like toLike(LikeRequest likeRequest)

    Like toLike(LikeResponse likeResponse)

    LikeResponse toLikeResponse(Like like)
}