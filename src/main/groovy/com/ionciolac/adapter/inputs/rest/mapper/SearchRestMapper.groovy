package com.ionciolac.adapter.inputs.rest.mapper

import com.ionciolac.adapter.inputs.rest.data.res.SearchResponse
import com.ionciolac.domain.model.User

interface SearchRestMapper {

    SearchResponse toSearchResponse(User user)
}