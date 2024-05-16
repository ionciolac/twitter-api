package com.ionciolac.adapter.inputs.rest.adapter

import com.ionciolac.adapter.inputs.rest.data.req.user.CreateUserRequest
import com.ionciolac.adapter.inputs.rest.data.req.user.UserRequest
import com.ionciolac.adapter.inputs.rest.data.res.UserResponse
import com.ionciolac.adapter.inputs.rest.mapper.UserRestMapper
import com.ionciolac.common.config.security.AuthenticatedUser
import com.ionciolac.port.inputs.UserInPort
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "twitter-api")
@RestController
@RequestMapping
class UserRestAdapter {

    private final UserRestMapper userRestMapper
    private final UserInPort userInPort

    UserRestAdapter(UserRestMapper userRestMapper, UserInPort userInPort) {
        this.userRestMapper = userRestMapper
        this.userInPort = userInPort
    }

    @PostMapping("user")
    ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        def user = userRestMapper.toUser(createUserRequest)
        user = userInPort.createUser(user)
        def userResponse = userRestMapper.toUserResponse(user)
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED)
    }

    @PutMapping("user")
    ResponseEntity<UserResponse> updateUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                            @RequestBody UserRequest userInfoRequest) {
        def user = userRestMapper.toUser(userInfoRequest)
        user.setId(authenticatedUser.getId())
        user = userInPort.updateUser(user)
        def userResponse = userRestMapper.toUserResponse(user)
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK)
    }

    @DeleteMapping("user")
    ResponseEntity deleteUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        userInPort.deleteUser(authenticatedUser.getId())
        return new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("user")
    ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        def user = userInPort.getUser(authenticatedUser.getId())
        def userResponse = userRestMapper.toUserResponse(user)
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK)
    }
}
