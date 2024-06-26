package com.ionciolac.common.model

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class PostMeta {

    long commentCount
    long likeCount
}
