package com.ionciolac.adapter.inputs.rest.data.req.user

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class UpdateUserRequest extends UserInfoRequest {
    String id
}
