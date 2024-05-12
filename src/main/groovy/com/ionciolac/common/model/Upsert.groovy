package com.ionciolac.common.model


import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

import java.time.Instant

@Data
@AllArgsConstructor
@NoArgsConstructor
class Upsert {
    @CreatedDate
    Instant createdOn
    @LastModifiedDate
    Instant updatedOn
}
