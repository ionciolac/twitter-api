package com.ionciolac.adapter.inputs.rest.mapper.impl

import com.ionciolac.adapter.inputs.rest.data.res.SearchResponse
import com.ionciolac.adapter.inputs.rest.mapper.SearchRestMapper
import com.ionciolac.domain.model.User
import org.springframework.stereotype.Component

@Component
class SearchRestMapperImpl implements SearchRestMapper {

    @Override
    SearchResponse toSearchResponse(User user) {
        return SearchResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build()
    }
}
