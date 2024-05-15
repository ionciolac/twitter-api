package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectAlreadyExistException
import com.ionciolac.common.util.CommonMessage
import com.ionciolac.domain.model.Follower
import com.ionciolac.port.inputs.FollowerInPort
import com.ionciolac.port.outputs.FollowerOutPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FollowerService implements FollowerInPort {

    FollowerOutPort followerOutPort

    FollowerService(FollowerOutPort followerOutPort) {
        this.followerOutPort = followerOutPort
    }

    @Override
    Follower createFollower(String authorizedUserId, Follower follower) {
        String followerId = follower.getFollowerId()
        String followingId = follower.getFollowingId()
        Optional<Follower> dbOptionalFollower = followerOutPort
                .getFollowerByFollowerIdAndFollowingId(followerId, followingId)
        if (dbOptionalFollower.isPresent()) {
            throw new ObjectAlreadyExistException(String.format(CommonMessage.USER_ALREADY_FOLLOWING_USER, followerId, followingId))
        } else
            return followerOutPort.createFollower(follower)
    }

    @Override
    void deleteFollower(String authorizedUserId, String id) {
        followerOutPort.deleteFollower(id)
    }

    @Override
    Page<Follower> getUserFollowers(String authorizedUserId, Pageable pageable) {
        return followerOutPort.getUserFollowers(authorizedUserId, pageable)
    }
}
