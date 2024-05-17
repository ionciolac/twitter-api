package com.ionciolac.domain.service

import com.ionciolac.common.exception.ObjectNotFoundException
import com.ionciolac.common.util.CommonMessage
import com.ionciolac.domain.model.Comment
import com.ionciolac.port.inputs.CommentInPort
import com.ionciolac.port.outputs.CommentOutPort
import org.apache.coyote.BadRequestException
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
        def id = comment.getId()
        def dbComment = getDBComment(id)
        if (authorizedUserId == dbComment.getUserId()) {
            dbComment.setComment(comment.getComment())
            return commentOutPort.upsertComment(dbComment)
        } else {
            String msg = String.format(CommonMessage.FOREIGN_MESSAGE, CommonMessage.EDIT, CommonMessage.COMMENT)
            throw new BadRequestException(msg)
        }
    }

    @Override
    void removeComment(String authorizedUserId, String id) {
        def dbComment = getDBComment(id)
        if (dbComment.getUserId() != authorizedUserId) {
            String msg = String.format(CommonMessage.FOREIGN_MESSAGE, CommonMessage.DELETE, CommonMessage.COMMENT)
            throw new BadRequestException(msg)
        }
        commentOutPort.removeComment(id)
    }

    @Override
    Comment getComment(String authorizedUserId, String id) {
        return getDBComment(id)
    }

    @Override
    Page<Comment> getPostComment(String authorizedUserId, String id, Pageable pageable) {
        return commentOutPort.getPostComments(id, pageable)
    }

    private def getDBComment(String id) {
        def comment = commentOutPort.getComment(id)
        if (comment.isPresent()) {
            return comment.get()
        } else {
            String msg = String.format(CommonMessage.NOT_FOUND_MESSAGE, CommonMessage.COMMENT, id)
            throw new ObjectNotFoundException(msg)
        }
    }
}
