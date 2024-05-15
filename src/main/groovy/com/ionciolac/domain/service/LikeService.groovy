package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectAlreadyExistException
import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.common.util.CommonMessage
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
            throw new ObjectAlreadyExistException(String.format(CommonMessage.USER_ALREADY_LIKED_POST, userId, postId))
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
            throw new ObjectNotFoundException(String.format(CommonMessage.NOT_FOUND_MESSAGE, CommonMessage.LIKE, id))
    }

    @Override
    List<Like> getPostLikes(String authorizedUserId, String id) {
        return likeOutPort.getPostLikes(id)
    }
}
