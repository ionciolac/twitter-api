package com.ionciolac.port.inputs

import com.ionciolac.domain.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostInPort {

    Post createPost(String authorizedUserId, Post post)

    Post updatePost(String authorizedUserId, Post post)

    void deletePost(String authorizedUserId, String id)

    Post getPost(String authorizedUserId, String id)

    Page<Post> getPostUser(String authorizedUserId, Pageable pageable)
}