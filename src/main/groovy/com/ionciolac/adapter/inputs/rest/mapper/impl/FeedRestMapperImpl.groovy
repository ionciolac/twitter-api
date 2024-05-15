package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.res.FeedResponse
import com.ionciolac.adapter.inputs.rest.mapper.FeedRestMapper
import com.ionciolac.domain.model.Feed
import org.springframework.stereotype.Component

@Component
class FeedRestMapperImpl implements FeedRestMapper {

    @Override
    FeedResponse toFeedResponse(Feed feed) {
        FeedResponse feedResponse = new FeedResponse()
        feedResponse.setId(feed.getId())
        feedResponse.setUserId(feed.getUserId())
        feedResponse.setPost(feed.getPost())
        feedResponse.setPostMeta(feed.getPostMeta())
        return feedResponse
    }
}
