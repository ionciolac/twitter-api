package com.ionciolac.port.inputs

import com.ionciolac.domain.model.Feed
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface FeedInPort {

    Page<Feed> getMyFeed(String authorizedUserId, Pageable pageable)

    Page<Feed> getUserMyFeed(String authorizedUserId, String userId, Pageable pageable)
}
