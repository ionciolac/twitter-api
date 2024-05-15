package com.ionciolac.adapter.outputs.persistence.adapter

import com.ionciolac.adapter.outputs.persistence.mapper.CommentPersistenceMapper
import com.ionciolac.adapter.outputs.persistence.repository.CommentRepository
import com.ionciolac.domain.model.Comment
import com.ionciolac.port.outputs.CommentOutPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class CommentPersistenceAdapter implements CommentOutPort {

    CommentPersistenceMapper commentPersistenceMapper
    CommentRepository commentRepository

    CommentPersistenceAdapter(CommentPersistenceMapper commentPersistenceMapper, CommentRepository commentRepository) {
        this.commentPersistenceMapper = commentPersistenceMapper
        this.commentRepository = commentRepository
    }

    @Override
    Comment upsertComment(Comment comment) {
        def commentEntity = commentRepository
                .save(commentPersistenceMapper.toCommentEntity(comment))
        return commentPersistenceMapper.toComment(commentEntity)
    }

    @Override
    void removeComment(String id) {
        commentRepository.deleteById(id)
    }

    @Override
    Optional<Comment> getComment(String id) {
        return commentRepository.findById(id).map { commentPersistenceMapper.toComment(it) }
    }

    @Override
    Page<Comment> getPostComments(String id, Pageable pageable) {
        return commentRepository.findAllByPostId(id, pageable)
                .map { commentPersistenceMapper.toComment(it) }
    }
}
