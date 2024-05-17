package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectAlreadyExistException
import com.ionciolac.common.util.CommonMessage
import com.ionciolac.domain.model.Follower
import com.ionciolac.port.inputs.FollowerInPort
import com.ionciolac.port.outputs.FollowerOutPort
import org.apache.coyote.BadRequestException
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
        def followerId = follower.getFollowerId()
        def followingId = follower.getFollowingId()
        checkIfUserAlreadyFollowingUser(followerId, followingId)
        checkIfUserIsTryingToFollowYourSelf(followerId, followingId)
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

    private void checkIfUserAlreadyFollowingUser(String followerId, String followingId) {
        def dbFollower = followerOutPort.getFollowerByFollowerIdAndFollowingId(followerId, followingId)
        if (dbFollower.isPresent()) {
            String msg = String.format(CommonMessage.USER_ALREADY_FOLLOWING_USER, followerId, followingId)
            throw new ObjectAlreadyExistException(msg)
        }
    }

    private void checkIfUserIsTryingToFollowYourSelf(String followerId, String followingId) {
        if (followerId == followingId) {
            String msg = CommonMessage.CANNOT_FOLLOW_YOUR_SELF
            throw new BadRequestException(msg)
        }
    }
}
