package com.ionciolac.adapter.inputs.rest.adapter

import com.ionciolac.adapter.inputs.rest.data.req.comment.CreateCommentRequest
import com.ionciolac.adapter.inputs.rest.data.req.comment.UpdateCommentRequest
import com.ionciolac.adapter.inputs.rest.data.res.CommentResponse
import com.ionciolac.adapter.inputs.rest.mapper.CommentRestMapper
import com.ionciolac.common.config.security.AuthenticatedUser
import com.ionciolac.port.inputs.CommentInPort
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "twitter-api")
@RestController
@RequestMapping
class CommentRestAdapter {

    CommentRestMapper commentRestMapper
    CommentInPort commentInPort

    CommentRestAdapter(CommentRestMapper commentRestMapper, CommentInPort commentInPort) {
        this.commentRestMapper = commentRestMapper
        this.commentInPort = commentInPort
    }

    @PostMapping("/comment")
    ResponseEntity<CommentResponse> createComment(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                  CreateCommentRequest createCommentRequest) {
        def comment = commentRestMapper.toComment(createCommentRequest)
        comment = commentInPort.createComment(authenticatedUser.getId(), comment)
        def commentResponse = commentRestMapper.toCommentResponse(comment)
        return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.CREATED)
    }

    @PutMapping("/comment")
    ResponseEntity<CommentResponse> updateComment(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                  UpdateCommentRequest updateCommentRequest) {
        def comment = commentRestMapper.toComment(updateCommentRequest)
        comment = commentInPort.updateComment(authenticatedUser.getId(), comment)
        def commentResponse = commentRestMapper.toCommentResponse(comment)
        return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.OK)
    }

    @DeleteMapping("/comment/{id}")
    ResponseEntity deleteComment(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                 @PathVariable("id") String id) {
        commentInPort.removeComment(authenticatedUser.getId(), id)
        return new ResponseEntity<>(HttpStatus.OK)
    }

    @GetMapping("comment/{id}")
    ResponseEntity<CommentResponse> getComment(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                               @PathVariable("id") String id) {
        def comment = commentInPort.getComment(authenticatedUser.getId(), id)
        def commentResponse = commentRestMapper.toCommentResponse(comment)
        return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.OK)
    }

    @GetMapping("post/{id}/comment")
    ResponseEntity<Page<CommentResponse>> getPostComments(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                          @PathVariable("id") String id,
                                                          Pageable pageable) {
        def commentResponse = commentInPort
                .getPostComment(authenticatedUser.getId(), id, pageable)
                .map { commentRestMapper.toCommentResponse(it) }
        return new ResponseEntity<Page<CommentResponse>>(commentResponse, HttpStatus.OK)
    }
}
