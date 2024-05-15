package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.domain.model.Comment
import com.ionciolac.port.inputs.CommentInPort
import com.ionciolac.port.outputs.CommentOutPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class CommentService implements CommentInPort {

    CommentOutPort commentOutPort

    CommentService(CommentOutPort commentOutPort) {
        this.commentOutPort = commentOutPort
    }

    @Override
    Comment createComment(String authorizedUserId, Comment comment) {
        return commentOutPort.upsertComment(comment)
    }

    @Override
    Comment updateComment(String authorizedUserId, Comment comment) {
        String commentId = comment.getId()
        Optional<Comment> dbOptionalComment = commentOutPort.getComment(commentId)
        if (dbOptionalComment.isPresent()) {
            Comment dbComment = dbOptionalComment.get()
            dbComment.setComment(comment.getComment())
            return commentOutPort.upsertComment(dbComment)
        }
        throw new ObjectNotFoundException(String.format("Comment Not found By Id %s", commentId))
    }

    @Override
    void removeComment(String authorizedUserId, String id) {
        commentOutPort.removeComment(id)
    }

    @Override
    Comment getComment(String authorizedUserId, String id) {
        Optional<Comment> dbOptionalComment = commentOutPort.getComment(id)
        if (dbOptionalComment.isPresent()) {
            return dbOptionalComment.get()
        } else
            throw new ObjectNotFoundException(String.format("Comment was Not found By Id %s", id))
    }

    @Override
    Page<Comment> getPostComment(String authorizedUserId, String id, Pageable pageable) {
        return commentOutPort.getPostComments(id, pageable)
    }
}
