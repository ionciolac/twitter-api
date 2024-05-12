package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.req.post.CreatePostRequest
import com.ionciolac.adapter.inputs.rest.data.req.post.UpdatePostRequest
import com.ionciolac.adapter.inputs.rest.data.res.PostResponse
import com.ionciolac.common.model.Post

interface PostRestMapper {

    Post toPost(CreatePostRequest createPostRequest)

    Post toPost(UpdatePostRequest updatePostRequest)

    PostResponse toPostResponse(Post post)
}