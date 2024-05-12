package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.req.post.CreatePostRequest
import com.ionciolac.adapter.inputs.rest.data.req.post.UpdatePostRequest
import com.ionciolac.adapter.inputs.rest.data.res.PostResponse
import com.ionciolac.adapter.inputs.rest.mapper.PostRestMapper
import com.ionciolac.adapter.inputs.rest.mapper.UserRestMapper
import com.ionciolac.common.model.Post
import org.springframework.stereotype.Component

@Component
class PostRestMapperImpl implements PostRestMapper {

    UserRestMapper userRestMapper

    PostRestMapperImpl(UserRestMapper userRestMapper) {
        this.userRestMapper = userRestMapper
    }

    @Override
    Post toPost(CreatePostRequest createPostRequest) {
        return Post.builder()
                .userId(createPostRequest.getUserId())
                .post(createPostRequest.getPost())
                .build()
    }

    @Override
    Post toPost(UpdatePostRequest updatePostRequest) {
        return Post.builder()
                .id(updatePostRequest.id)
                .post(updatePostRequest.getPost())
                .build()
    }

    @Override
    PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.id)
                .userId(post.getUserId())
                .post(post.getPost())
                .build()
    }
}
