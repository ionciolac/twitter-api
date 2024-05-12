package com.ionciolac.adapter.outputs.persistence.mapper.impl

import com.ionciolac.adapter.outputs.persistence.entity.PostEntity
import com.ionciolac.adapter.outputs.persistence.mapper.PostPersistenceMapper
import com.ionciolac.common.model.Post
import org.springframework.stereotype.Component

@Component
class PostPersistenceMapperImpl implements PostPersistenceMapper {
    @Override
    Post toPost(PostEntity postEntity) {
        return Post.builder()
                .id(postEntity.getId())
                .userId(postEntity.getUserId())
                .post(postEntity.getPost())
                .build()
    }

    @Override
    PostEntity toPostEntity(Post post) {
        return PostEntity.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .post(post.getPost())
                .build()
    }
}
