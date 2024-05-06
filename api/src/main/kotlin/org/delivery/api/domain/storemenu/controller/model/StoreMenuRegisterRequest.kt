package org.delivery.api.domain.storemenu.controller.model

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class StoreMenuRegisterRequest(
    @field:NotNull
    var storeId: Long,
    @field:NotBlank
    var name: String,
    @field:NotNull
    var amount: BigDecimal,
    @field:NotBlank
    var thumbnailUrl: String
){
    override fun toString(): String {
        return "StoreMenuRegisterRequest(storeId=$storeId, name=$name, amount=$amount, thumbnailUrl=$thumbnailUrl)"
    }
}
