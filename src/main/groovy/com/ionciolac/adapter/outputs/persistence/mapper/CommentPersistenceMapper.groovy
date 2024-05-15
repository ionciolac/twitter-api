package com.ionciolac.adapter.outputs.persistence.mapper

import com.ionciolac.adapter.outputs.persistence.entity.CommentEntity
import com.ionciolac.domain.model.Comment

interface CommentPersistenceMapper {

    Comment toComment(CommentEntity commentEntity)

    CommentEntity toCommentEntity(Comment comment)
}