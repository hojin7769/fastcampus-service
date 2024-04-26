package org.delivery.api.domain.store.controller.model

import org.delivery.db.store.enums.StoreCategory
import java.math.BigDecimal

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @project Mobile_Project
 * @package org.delivery.api.domain.store.controller.model
 * @author hojin
 * @date 2024-04-26
 * @explanation
 * @version 1.0
 */
data class StoreRegisterRequest(
        @NotNull
        var name: String,
        @NotBlank
        var address: String,
        @NotNull
        var category: StoreCategory,
        @NotNull
        var thumbnailUrl: String,
        @NotNull
        var minimumAmount : BigDecimal,
        @NotNull
        var minimumDeliveryAmount : BigDecimal,
        @NotBlank
        var phoneNumber : String

) {

    override fun toString(): String {
        return "StoreRegisterRequest(name='$name', address='$address', category=$category, thumbnailUrl='$thumbnailUrl', minimumAmount=$minimumAmount, minimumDeliveryAmount=$minimumDeliveryAmount, phoneNumber='$phoneNumber')"
    }
}