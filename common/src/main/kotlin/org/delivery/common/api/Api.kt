package org.delivery.common.api

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

data class Api<T> (
    var result: Result?= null,

    var body: T?= null
)