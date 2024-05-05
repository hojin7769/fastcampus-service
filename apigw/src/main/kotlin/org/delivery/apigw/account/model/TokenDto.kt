package org.delivery.apigw.account.model

import java.time.LocalDateTime

data class TokenDto(
    var token: String?=null,
    var expiredAt:LocalDateTime?=null
)
