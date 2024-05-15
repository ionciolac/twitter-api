package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectAlreadyExistException
import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.domain.model.Like
import com.ionciolac.port.inputs.LikeInPort
import com.ionciolac.port.outputs.LikeOutPort
import org.springframework.stereotype.Service

@Service
class LikeService implements LikeInPort {

    LikeOutPort likeOutPort

    LikeService(LikeOutPort likeOutPort) {
        this.likeOutPort = likeOutPort
    }

    @Override
    Like createLike(String authorizedUserId, Like like) {
        String userId = like.getUserId()
        String postId = like.getPostId()
        Optional<Like> dbOptionalLike = likeOutPort.getLikeByUserAndPost(userId, postId)
        if (dbOptionalLike.isPresent())
            throw new ObjectAlreadyExistException(String.format("Like to the post %s by user %s already exist", postId, userId))
        else
            return likeOutPort.createLike(like)
    }

    @Override
    void deleteLike(String authorizedUserId, String id) {
        likeOutPort.deleteLike(id)
    }

    @Override
    Like getLike(String authorizedUserId, String id) {
        Optional<Like> dbOptionalLike = likeOutPort.getLike(id)
        if (dbOptionalLike.isPresent())
            return dbOptionalLike.get()
        else
            throw new ObjectNotFoundException(String.format("Like was Not found By Id %s", id))
    }

    @Override
    List<Like> getPostLikes(String authorizedUserId, String id) {
        return likeOutPort.getPostLikes(id)
    }
}
