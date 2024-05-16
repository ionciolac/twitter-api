package com.ionciolac.adapter.inputs.rest.adapter

import com.ionciolac.adapter.inputs.rest.data.req.search.SearchRequest
import com.ionciolac.adapter.inputs.rest.data.res.SearchResponse
import com.ionciolac.adapter.inputs.rest.mapper.SearchRestMapper
import com.ionciolac.common.config.security.AuthenticatedUser
import com.ionciolac.port.inputs.SearchInPort
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SecurityRequirement(name = "twitter-api")
@RestController
@RequestMapping
class SearchRestAdapter {

    SearchRestMapper searchRestMapper
    SearchInPort searchInPort

    SearchRestAdapter(SearchRestMapper searchRestMapper, SearchInPort searchInPort) {
        this.searchRestMapper = searchRestMapper
        this.searchInPort = searchInPort
    }

    @GetMapping("/search-user")
    ResponseEntity<Page<SearchResponse>> searchUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                    @RequestBody SearchRequest searchRequest) {
        def pageable = getPageable(searchRequest)
        def response = searchInPort.searchUser(authenticatedUser.getId(), searchRequest.getFirstName(),
                searchRequest.getLastName(), pageable)
                .map { searchRestMapper.toSearchResponse(it) }
        return new ResponseEntity<Page<SearchResponse>>(response, HttpStatus.OK)
    }

    def getPageable = { SearchRequest searchRequest ->
        if (searchRequest.getSort() == null) {
            return PageRequest.of(searchRequest.getPage(), searchRequest.getSize())
        } else {
            return PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), searchRequest.getSort())
        }
    }
}