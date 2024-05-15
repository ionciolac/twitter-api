package com.ionciolac.port.inputs

import com.ionciolac.domain.model.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface CommentInPort {

    Comment createComment(Comment comment)

    Comment updateComment(String authorizedUserId, Comment comment)

    void removeComment(String authorizedUserId, String id)

    Comment getComment(String authorizedUserId, String id)

    Page<Comment> getPostComment(String authorizedUserId, String id, Pageable pageable)
}
