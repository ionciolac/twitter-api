package com.ionciolac.domain.model

import com.ionciolac.common.model.Upsert
import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class Comment extends Upsert {

    String id
    String userId
    String postId
    String comment
}
