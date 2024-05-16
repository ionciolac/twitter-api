package com.ionciolac.adapter.inputs.rest.adapter

import com.ionciolac.adapter.inputs.rest.data.req.follower.FollowerRequest
import com.ionciolac.adapter.inputs.rest.data.res.FollowerResponse
import com.ionciolac.adapter.inputs.rest.mapper.FollowerRestMapper
import com.ionciolac.common.config.security.AuthenticatedUser
import com.ionciolac.port.inputs.FollowerInPort
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
class FollowerRestAdapter {

    FollowerRestMapper followerRestMapper
    FollowerInPort followerInPort

    FollowerRestAdapter(FollowerRestMapper followerRestMapper, FollowerInPort followerInPort) {
        this.followerRestMapper = followerRestMapper
        this.followerInPort = followerInPort
    }

    @PostMapping("/follow")
    ResponseEntity<FollowerResponse> createFollower(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                    @RequestBody FollowerRequest followerRequest) {
        def follower = followerRestMapper.toFollower(followerRequest)
        follower = followerInPort.createFollower(authenticatedUser.getId(), follower)
        def followerResponse = followerRestMapper.toFollowerResponse(follower)
        return new ResponseEntity<FollowerResponse>(followerResponse, HttpStatus.CREATED)
    }

    @DeleteMapping("/un-follow/{id}")
    ResponseEntity deleteFollower(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                  @PathVariable("id") String id) {
        followerInPort.deleteFollower(authenticatedUser.getId(), id)
        return new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/my-followers")
    ResponseEntity<Page<FollowerResponse>> myFollowers(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                       @RequestBody Pageable pageable) {
        def followers = followerInPort.getUserFollowers(authenticatedUser.getId(), pageable)
                .map { followerRestMapper.toFollowerResponse(it) }
                .toList()
        return new ResponseEntity(followers, HttpStatus.OK)
    }
}
