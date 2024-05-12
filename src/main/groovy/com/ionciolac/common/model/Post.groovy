package com.ionciolac.common.model


import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class Post extends Upsert {
    String id
    String userId
    String post
}
