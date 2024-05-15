package com.ionciolac.port.inputs

import com.ionciolac.domain.model.Like

interface LikeInPort {

    Like createLike(String authorizedUserId, Like like)

    void deleteLike(String authorizedUserId, String id)

    Like getLike(String authorizedUserId, String id)

    List<Like> getPostLikes(String authorizedUserId, String id)
}