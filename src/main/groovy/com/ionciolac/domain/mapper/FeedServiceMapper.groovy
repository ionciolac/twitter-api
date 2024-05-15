package com.ionciolac.domain.mapper

import com.ionciolac.domain.model.Feed
import com.ionciolac.domain.model.Post

interface FeedServiceMapper {

    Feed toFeed(Post post)
}
