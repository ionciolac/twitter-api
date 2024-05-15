package com.ionciolac.domain.mapper.impl


import com.ionciolac.domain.mapper.FeedServiceMapper
import com.ionciolac.domain.model.Feed
import com.ionciolac.domain.model.Post
import org.springframework.stereotype.Component

@Component
class FeedServiceMapperImpl implements FeedServiceMapper {

    @Override
    Feed toFeed(Post post) {
        Feed Feed = new Feed()
        Feed.setId(post.getId())
        Feed.setUserId(post.getUserId())
        Feed.setPost(post.getPost())
        return Feed
    }


}
