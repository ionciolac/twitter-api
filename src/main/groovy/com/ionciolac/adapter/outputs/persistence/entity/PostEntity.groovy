package com.ionciolac.adapter.outputs.persistence.entity

import com.ionciolac.common.model.Upsert
import groovy.transform.builder.Builder
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
class PostEntity extends Upsert {

    @Id
    String id
    String userId
    String post
}
