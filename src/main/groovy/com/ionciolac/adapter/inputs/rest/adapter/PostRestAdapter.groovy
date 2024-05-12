package com.ionciolac.adapter.inputs.rest.adapter

import com.ionciolac.adapter.inputs.rest.data.req.post.CreatePostRequest
import com.ionciolac.adapter.inputs.rest.data.req.post.UpdatePostRequest
import com.ionciolac.adapter.inputs.rest.data.res.PostResponse
import com.ionciolac.adapter.inputs.rest.mapper.PostRestMapper
import com.ionciolac.port.inputs.PostInPort
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "twitter-api")
@RestController
@RequestMapping("post")
class PostRestAdapter {

    PostRestMapper postRestMapper
    PostInPort postInPort

    PostRestAdapter(PostRestMapper postRestMapper, PostInPort postInPort) {
        this.postRestMapper = postRestMapper
        this.postInPort = postInPort
    }

    @PreAuthorize("hasRole('CAN_ACCESS')")
    @PostMapping
    ResponseEntity<PostResponse> createPost(CreatePostRequest createPostRequest) {
        def post = postRestMapper.toPost(createPostRequest)
        post = postInPort.createPost(post)
        def userResponse = postRestMapper.toPostResponse(post)
        return new ResponseEntity<PostResponse>(userResponse, HttpStatus.CREATED)
    }

    @PreAuthorize("hasRole('CAN_ACCESS')")
    @PutMapping
    ResponseEntity<PostResponse> updatePost(UpdatePostRequest updatePostRequest) {
        def post = postRestMapper.toPost(updatePostRequest)
        post = postInPort.updatePost(post)
        def userResponse = postRestMapper.toPostResponse(post)
        return new ResponseEntity<PostResponse>(userResponse, HttpStatus.OK)
    }

    @PreAuthorize("hasRole('CAN_ACCESS')")
    @DeleteMapping("/{id}")
    ResponseEntity deletePost(@PathVariable("id") String id) {
        postInPort.deletePost(id)
        return new ResponseEntity(HttpStatus.OK)
    }

    @PreAuthorize("hasRole('CAN_ACCESS')")
    @GetMapping("/{id}")
    ResponseEntity<PostResponse> getPost(@PathVariable("id") String id) {
        def post = postInPort.getPost(id)
        def postResponse = postRestMapper.toPostResponse(post)
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK)
    }

    @PreAuthorize("hasRole('CAN_ACCESS')")
    @GetMapping("/user/{user-id}")
    ResponseEntity<Page<PostResponse>> getUserPosts(@PathVariable("user-id") String userId, Pageable pageable) {
        def userPosts = postInPort.getPost(userId, pageable)
                .map { val -> postRestMapper.toPostResponse(val) }
        return new ResponseEntity<Page<PostResponse>>(userPosts, HttpStatus.OK)
    }
}
