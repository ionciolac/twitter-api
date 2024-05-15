package com.ionciolac.adapter.outputs.persistence.adapter


import com.ionciolac.adapter.outputs.persistence.mapper.LikePersistenceMapper
import com.ionciolac.adapter.outputs.persistence.repository.LikeRepository
import com.ionciolac.domain.model.Like
import com.ionciolac.port.outputs.LikeOutPort
import org.springframework.stereotype.Service

@Service
class LikePersistenceAdapter implements LikeOutPort {

    LikePersistenceMapper likePersistenceMapper
    LikeRepository likeRepository

    LikePersistenceAdapter(LikePersistenceMapper likePersistenceMapper, LikeRepository likeRepository) {
        this.likePersistenceMapper = likePersistenceMapper
        this.likeRepository = likeRepository
    }

    @Override
    Like createLike(Like like) {
        def likeEntity = likePersistenceMapper.toLikeEntity(like)
        likeEntity = likeRepository.save(likeEntity)
        return likePersistenceMapper.toLike(likeEntity)
    }

    @Override
    void deleteLike(String id) {
        likeRepository.deleteById(id)
    }

    @Override
    Optional<Like> getLike(String id) {
        return likeRepository.findById(id).map { likePersistenceMapper.toLike(it) }
    }

    @Override
    List<Like> getPostLikes(String id) {
        return likeRepository.findAllByPostId(id)
                .stream()
                .map { likePersistenceMapper.toLike(it) }
                .toList()
    }

    @Override
    Optional<Like> getLikeByUserAndPost(String userId, String postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId)
                .map { likePersistenceMapper.toLike(it) }
    }

    @Override
    long getPostLikesCount(String id) {
        return likeRepository.countByPostId(id)
    }
}
