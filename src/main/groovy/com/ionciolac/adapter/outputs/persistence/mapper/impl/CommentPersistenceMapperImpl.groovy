package com.ionciolac.adapter.outputs.persistence.mapper.impl

import com.ionciolac.adapter.outputs.persistence.entity.CommentEntity
import com.ionciolac.adapter.outputs.persistence.mapper.CommentPersistenceMapper
import com.ionciolac.domain.model.Comment
import org.springframework.stereotype.Component

@Component
class CommentPersistenceMapperImpl implements CommentPersistenceMapper {

    @Override
    Comment toComment(CommentEntity commentEntity) {
        Comment comment = new Comment()
        comment.setId(commentEntity.getId())
        comment.setUserId(commentEntity.getUserId())
        comment.setPostId(commentEntity.getPostId())
        comment.setComment(commentEntity.getComment())
        comment.setCreatedOn(commentEntity.getCreatedOn())
        comment.setUpdatedOn(commentEntity.getUpdatedOn())
        return comment
    }

    @Override
    CommentEntity toCommentEntity(Comment comment) {
        CommentEntity commentEntity = new CommentEntity()
        commentEntity.setId(comment.getId())
        commentEntity.setUserId(comment.getUserId())
        commentEntity.setPostId(comment.getPostId())
        commentEntity.setComment(comment.getComment())
        commentEntity.setCreatedOn(comment.getCreatedOn())
        commentEntity.setUpdatedOn(comment.getUpdatedOn())
        return commentEntity
    }
}
