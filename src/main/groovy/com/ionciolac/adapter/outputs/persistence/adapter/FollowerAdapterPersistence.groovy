package com.ionciolac.adapter.outputs.persistence.adapter

import com.ionciolac.adapter.outputs.persistence.mapper.FollowerPersistenceMapper
import com.ionciolac.adapter.outputs.persistence.repository.FollowerRepository
import com.ionciolac.domain.model.Follower
import com.ionciolac.port.outputs.FollowerOutPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FollowerAdapterPersistence implements FollowerOutPort {

    FollowerPersistenceMapper followerPersistenceMapper
    FollowerRepository followerRepository

    FollowerAdapterPersistence(FollowerPersistenceMapper followerPersistenceMapper, FollowerRepository followerRepository) {
        this.followerPersistenceMapper = followerPersistenceMapper
        this.followerRepository = followerRepository
    }

    @Override
    Follower createFollower(Follower follower) {
        def followerEntity = followerPersistenceMapper.toFollowerEntity(follower)
        followerEntity = followerRepository.save(followerEntity)
        return followerPersistenceMapper.toFollower(followerEntity)
    }

    @Override
    void deleteFollower(String id) {
        followerRepository.deleteById(id)
    }

    @Override
    Page<Follower> getUserFollowers(String id, Pageable pageable) {
        return followerRepository.findAllByFollowerId(id, pageable)
                .map { followerPersistenceMapper.toFollower(it) }
    }

    @Override
    Optional<Follower> getFollowerByFollowerIdAndFollowingId(String followerId, String followingId) {
        return followerRepository.findByFollowerIdAndFollowingId(followerId, followingId)
                .map { followerPersistenceMapper.toFollower(it) }
    }

    @Override
    List<String> getUserFollowers(String id) {
        return followerRepository.findAllByFollowerId(id)
                .stream()
                .map { it.getFollowingId() }
                .toList()
    }
}
