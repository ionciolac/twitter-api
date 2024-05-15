package com.ionciolac.adapter.inputs.rest.adapter

import com.ionciolac.adapter.inputs.rest.data.res.FeedResponse
import com.ionciolac.adapter.inputs.rest.mapper.FeedRestMapper
import com.ionciolac.common.config.security.AuthenticatedUser
import com.ionciolac.port.inputs.FeedInPort
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SecurityRequirement(name = "twitter-api")
@RestController
@RequestMapping
class FeedRestAdapter {

    FeedRestMapper feedRestMapper
    FeedInPort feedInPort

    FeedRestAdapter(FeedRestMapper feedRestMapper, FeedInPort feedInPort) {
        this.feedRestMapper = feedRestMapper
        this.feedInPort = feedInPort
    }

    @GetMapping("/my-feed")
    ResponseEntity<Page<FeedResponse>> getMyFeed(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                 Pageable pageable) {
        def feedResponse = feedInPort.getMyFeed(authenticatedUser.getId(), pageable)
                .map { feedRestMapper.toFeedResponse(it) }
        return new ResponseEntity<Page<FeedResponse>>(feedResponse, HttpStatus.OK)
    }

    @GetMapping("user/{id}/my-feed")
    ResponseEntity<Page<FeedResponse>> userMyFeed(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                  @PathVariable("id") String id, Pageable pageable) {
        def feedResponse = feedInPort.getUserMyFeed(authenticatedUser.getId(), id, pageable)
                .map { feedRestMapper.toFeedResponse(it) }
        return new ResponseEntity<Page<FeedResponse>>(feedResponse, HttpStatus.OK)
    }
}
