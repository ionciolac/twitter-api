package com.ionciolac.adapter.inputs.rest.data.res

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class UserResponse {
    String id
    String username
    @JsonProperty(value = "first_name")
    String firstName
    @JsonProperty(value = "last_name")
    String lastName
    String email
    @JsonProperty(value = "phone_number")
    String phoneNumber
}
