package com.ionciolac.adapter.inputs.rest.data.res


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
    String firstName
    String lastName
    String email
    String phoneNumber
}
