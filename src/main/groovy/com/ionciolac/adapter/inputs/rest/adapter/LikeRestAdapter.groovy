package com.ionciolac.adapter.inputs.rest.adapter

import com.ionciolac.adapter.inputs.rest.data.req.like.LikeRequest
import com.ionciolac.adapter.inputs.rest.data.res.LikeResponse
import com.ionciolac.adapter.inputs.rest.mapper.LikeRestMapper
import com.ionciolac.common.config.security.AuthenticatedUser
import com.ionciolac.port.inputs.LikeInPort
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "twitter-api")
@RestController
@RequestMapping
class LikeRestAdapter {

    LikeRestMapper likeRestMapper
    LikeInPort likeInPort

    LikeRestAdapter(LikeRestMapper likeRestMapper, LikeInPort likeInPort) {
        this.likeRestMapper = likeRestMapper
        this.likeInPort = likeInPort
    }

    @PostMapping("/like")
    ResponseEntity<LikeResponse> createLike(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                            LikeRequest likeRequest) {
        def like = likeRestMapper.toLike(likeRequest)
        like = likeInPort.createLike(authenticatedUser.getId(), like)
        def likeResponse = likeRestMapper.toLikeResponse(like)
        return new ResponseEntity<LikeResponse>(likeResponse, HttpStatus.CREATED)
    }

    @DeleteMapping("/like{id}")
    ResponseEntity<LikeResponse> deleteLike(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                            @PathVariable("id") String id) {
        likeInPort.deleteLike(authenticatedUser.getId(), id)
    }

    @GetMapping("/like/{id}")
    ResponseEntity<LikeResponse> getLike(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                         @PathVariable("id") String id) {
        def like = likeInPort.getLike(authenticatedUser.getId(), id)
        def likeResponse = likeRestMapper.toLikeResponse(like)
        return new ResponseEntity<LikeResponse>(likeResponse, HttpStatus.OK)
    }

    @GetMapping("/post/{id}like")
    ResponseEntity<List<LikeResponse>> getPostLike(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                   @PathVariable("id") String id) {
        def likeResponse = likeInPort.getPostLikes(authenticatedUser.getId(), id)
                .stream()
                .map { likeRestMapper.toLikeResponse(it) }
                .toList()
        return new ResponseEntity<List<LikeResponse>>(likeResponse, HttpStatus.OK)
    }
}
