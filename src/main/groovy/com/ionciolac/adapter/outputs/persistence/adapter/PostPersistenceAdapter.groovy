package com.ionciolac.adapter.outputs.persistence.adapter

import com.ionciolac.adapter.outputs.persistence.entity.PostEntity
import com.ionciolac.adapter.outputs.persistence.mapper.PostPersistenceMapper
import com.ionciolac.adapter.outputs.persistence.repository.PostRepository
import com.ionciolac.common.model.Post
import com.ionciolac.port.outputs.PostOutPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostPersistenceAdapter implements PostOutPort {

    PostPersistenceMapper postPersistenceMapper
    PostRepository postRepository

    PostPersistenceAdapter(PostPersistenceMapper postPersistenceMapper, PostRepository postRepository) {
        this.postPersistenceMapper = postPersistenceMapper
        this.postRepository = postRepository
    }

    @Override
    Post upsertPost(Post post) {
        PostEntity postEntity = postPersistenceMapper.toPostEntity(post)
        postEntity = postRepository.save(postEntity)
        return postPersistenceMapper.toPost(postEntity)
    }

    @Override
    void deletePost(String id) {
        postRepository.deleteById(id)
    }

    @Override
    Optional<Post> getPost(String id) {
        return postRepository.findById(id).map { postPersistenceMapper.toPost(it) }
    }

    @Override
    Page<Post> getPost(String userId, Pageable pageable) {
        return postRepository
                .findAllByUserId(userId, pageable)
                .map { { postPersistenceMapper.toPost(it) } }
    }
}
