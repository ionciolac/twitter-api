package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.res.FeedResponse
import com.ionciolac.domain.model.Feed

interface FeedRestMapper {

    FeedResponse toFeedResponse(Feed feed)
}