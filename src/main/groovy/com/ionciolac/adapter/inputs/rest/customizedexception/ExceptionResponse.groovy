package com.ionciolac.adapter.inputs.rest.customizedexception

import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

import java.time.Instant

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class ExceptionResponse {

    Instant date
    String message
}
