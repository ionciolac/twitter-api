package com.ionciolac.domain.model

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    String id
    String login
    String firstName
    String lastName
    String email
    String phoneNumber
    String password
}
