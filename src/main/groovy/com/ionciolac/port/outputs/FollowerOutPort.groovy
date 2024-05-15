package com.ionciolac.port.outputs

import com.ionciolac.domain.model.Follower
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FollowerOutPort {

    Follower createFollower(Follower follower)

    void deleteFollower(String id)

    Page<Follower> getUserFollowers(String id, Pageable pageable)

    Optional<Follower> getFollowerByFollowerIdAndFollowingId(String followerId, String followingId)

    List<String> getUserFollowers(String id)
}