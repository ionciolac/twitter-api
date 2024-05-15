package com.ionciolac.adapter.inputs.rest.data.req.comment

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class CreateCommentRequest {

    String userId
    String postId
    String comment
}
