package com.ionciolac.adapter.inputs.rest.data.req.search

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.domain.Sort

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class SearchRequest {

    String firstName
    String lastName
    int page
    int size
    Sort sort
}
