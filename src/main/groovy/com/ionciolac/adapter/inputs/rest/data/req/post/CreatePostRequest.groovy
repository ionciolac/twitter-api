package com.ionciolac.adapter.inputs.rest.data.req.post


import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class CreatePostRequest {

    String userId
    String post
}
