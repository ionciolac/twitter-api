package com.ionciolac.adapter.inputs.rest.data.res

import com.ionciolac.common.model.PostMeta
import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class FeedResponse extends PostResponse {

    PostMeta postMeta
}
