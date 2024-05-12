package com.ionciolac.adapter.inputs.rest.adapter

import com.ionciolac.adapter.inputs.rest.data.req.user.CreateUserRequest
import com.ionciolac.adapter.inputs.rest.data.req.user.UpdateUserRequest
import com.ionciolac.adapter.inputs.rest.data.res.UserResponse
import com.ionciolac.adapter.inputs.rest.mapper.UserRestMapper
import com.ionciolac.port.inputs.UserInPort
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "twitter-api")
@RestController
@RequestMapping("user")
class UserRestAdapter {

    private final UserRestMapper userRestMapper
    private final UserInPort userInPort

    UserRestAdapter(UserRestMapper userRestMapper, UserInPort userInPort) {
        this.userRestMapper = userRestMapper
        this.userInPort = userInPort
    }

    @PreAuthorize("user")
    @PostMapping
    ResponseEntity<UserResponse> createUser(CreateUserRequest createUserRequest) {
        def user = userRestMapper.toUser(createUserRequest)
        user = userInPort.createUser(user)
        def userResponse = userRestMapper.toUserResponse(user)
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED)
    }

    @PutMapping
    ResponseEntity<UserResponse> updateUser(UpdateUserRequest updateUserRequest) {
        def user = userRestMapper.toUser(updateUserRequest)
        user = userInPort.updateUser(user)
        def userResponse = userRestMapper.toUserResponse(user)
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable("id") String id) {
        userInPort.deleteUser(id)
        return new ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUser(@PathVariable("id") String id) {
        def user = userInPort.getUser(id)
        def userResponse = userRestMapper.toUserResponse(user)
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK)
    }
}
