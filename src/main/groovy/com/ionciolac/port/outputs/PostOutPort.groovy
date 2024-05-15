package com.ionciolac.port.outputs

import com.ionciolac.domain.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostOutPort {

    Post upsertPost(Post post)

    void deletePost(String id)

    Optional<Post> getPost(String id)

    Page<Post> getPost(String userId, Pageable pageable)

    Page<Post> getPostsByUsersId(List<String> ids, Pageable pageable)
}