package com.ionciolac.adapter.inputs.rest.data.req

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class CreateUserRequest extends UserRequest {
    String password
}
