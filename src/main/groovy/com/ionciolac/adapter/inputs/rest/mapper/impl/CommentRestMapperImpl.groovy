package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.req.comment.CreateCommentRequest
import com.ionciolac.adapter.inputs.rest.data.req.comment.UpdateCommentRequest
import com.ionciolac.adapter.inputs.rest.data.res.CommentResponse
import com.ionciolac.adapter.inputs.rest.mapper.CommentRestMapper
import com.ionciolac.domain.model.Comment
import org.springframework.stereotype.Component

@Component
class CommentRestMapperImpl implements CommentRestMapper {

    @Override
    Comment toComment(CreateCommentRequest commentRequest) {
        return Comment.builder()
                .userId(commentRequest.getUserId())
                .postId(commentRequest.getPostId())
                .comment(commentRequest.getComment())
                .build()
    }

    @Override
    Comment toComment(UpdateCommentRequest updateCommentRequest) {
        return Comment.builder()
                .id(updateCommentRequest.getId())
                .comment(updateCommentRequest.getComment())
                .build()
    }

    @Override
    CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .postId(comment.getPostId())
                .comment(comment.getComment())
                .build()
    }
}
