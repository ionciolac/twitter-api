package com.ionciolac.adapter.outputs.persistence.mapper.impl

import com.ionciolac.adapter.outputs.persistence.entity.PostEntity
import com.ionciolac.adapter.outputs.persistence.mapper.PostPersistenceMapper
import com.ionciolac.domain.model.Post
import org.springframework.stereotype.Component

@Component
class PostPersistenceMapperImpl implements PostPersistenceMapper {
    @Override
    Post toPost(PostEntity postEntity) {
        Post post = new Post()
        post.setId(postEntity.getId())
        post.setUserId(postEntity.getUserId())
        post.setPost(postEntity.getPost())
        post.setCreatedOn(postEntity.getCreatedOn())
        post.setUpdatedOn(postEntity.getUpdatedOn())
        return post
    }

    @Override
    PostEntity toPostEntity(Post post) {
        PostEntity postEntity = new PostEntity()
        postEntity.setId(post.getId())
        postEntity.setUserId(post.getUserId())
        postEntity.setPost(post.getPost())
        postEntity.setCreatedOn(post.getCreatedOn())
        postEntity.setUpdatedOn(post.getUpdatedOn())
        return postEntity
    }
}
