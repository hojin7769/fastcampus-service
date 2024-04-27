package org.delivery.api.domain.storemenu.controller.model

import org.delivery.db.storemenu.enums.StoreMenuStatus
import java.math.BigDecimal

data class StoreMenuResponse(
    var id: Long? = null,
    var storeId: Long? = null,
    var name: String? = null,
    var amount: BigDecimal? = null,
    val status: StoreMenuStatus? = null,
    val thumbnailUrl: String? = null,
    val likeCount: Int? = 0,
    val sequence: Int? = 0
)
