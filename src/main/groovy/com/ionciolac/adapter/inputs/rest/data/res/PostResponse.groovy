package com.ionciolac.adapter.inputs.rest.data.res

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class PostResponse {
    String id
    String userId
    String post
}
