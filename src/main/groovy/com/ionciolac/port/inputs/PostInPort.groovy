package com.ionciolac.port.inputs

import com.ionciolac.common.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostInPort {

    Post createPost(Post post)

    Post updatePost(Post post)

    void deletePost(String id)

    Post getPost(String id)

    Page<Post> getPost(String userId, Pageable pageable)
}