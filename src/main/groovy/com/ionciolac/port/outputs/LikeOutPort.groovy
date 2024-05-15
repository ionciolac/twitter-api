package com.ionciolac.port.outputs

import com.ionciolac.domain.model.Like

interface LikeOutPort {

    Like createLike(Like like)

    void deleteLike(String id)

    Optional<Like> getLike(String id)

    List<Like> getPostLikes(String id)

    Optional<Like> getLikeByUserAndPost(String userId, String postId)
}