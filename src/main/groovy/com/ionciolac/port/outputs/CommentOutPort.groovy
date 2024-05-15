package com.ionciolac.port.outputs

import com.ionciolac.domain.model.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CommentOutPort {

    Comment upsertComment(Comment comment)

    void removeComment(String id)

    Optional<Comment> getComment(String id)

    Page<Comment> getPostComments(String id, Pageable pageable)
}