package com.ionciolac.domain.model

import com.ionciolac.common.model.PostMeta
import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class Feed extends Post {

    PostMeta postMeta
}
