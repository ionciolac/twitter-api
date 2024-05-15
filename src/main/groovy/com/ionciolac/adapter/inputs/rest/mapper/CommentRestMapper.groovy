package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.req.comment.CreateCommentRequest
import com.ionciolac.adapter.inputs.rest.data.req.comment.UpdateCommentRequest
import com.ionciolac.adapter.inputs.rest.data.res.CommentResponse
import com.ionciolac.domain.model.Comment

interface CommentRestMapper {

    Comment toComment(CreateCommentRequest commentRequest)

    Comment toComment(UpdateCommentRequest updateCommentRequest)

    CommentResponse toCommentResponse(Comment comment)
}