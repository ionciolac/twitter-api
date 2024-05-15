package com.ionciolac.port.inputs

import com.ionciolac.domain.model.Follower
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FollowerInPort {

    Follower createFollower(String authorizedUserId, Follower follower)

    void deleteFollower(String authorizedUserId, String id)

    Page<Follower> getUserFollowers(String authorizedUserId, Pageable pageable)
}