package com.ionciolac.adapter.inputs.rest.data.req.follower

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class FollowerRequest {

    String followerId
    String followingId
}
