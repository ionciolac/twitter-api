package com.ionciolac.common.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

import java.time.Instant

@Data
@AllArgsConstructor
@NoArgsConstructor
class Upsert {
    Instant createdOn;
    Instant updatedOn;
}
