package com.ionciolac.adapter.inputs.rest.data.req.like

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class LikeRequest {

    String userId
    String postId
}
