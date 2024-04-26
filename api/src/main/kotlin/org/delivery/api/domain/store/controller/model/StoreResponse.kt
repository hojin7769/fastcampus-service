package org.delivery.api.domain.store.controller.model

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import java.math.BigDecimal

/**
 * @project Mobile_Project
 * @package org.delivery.api.domain.store.controller.model
 * @author hojin
 * @date 2024-04-26
 * @explanation
 * @version 1.0
 */
data class StoreResponse(
        var id: Long?=null,
        var name: String?=null,
        var address: String?=null,
        var status: StoreStatus?=null,
        var category: StoreCategory?=null,
        var star:Double?=null,
        var thumbnailUrl: String?=null,
        var minimumAmount : BigDecimal?=null,
        var minimumDeliveryAmount : BigDecimal?=null,
        var phoneNumber : String?=null
) {
    override fun toString(): String {
        return "StoreResponse(id=$id, name='$name', address='$address', category='$category', thumbnailUrl='$thumbnailUrl', minimumAmount='$minimumAmount', minimumDeliveryAmount='$minimumDeliveryAmount', phoneNumber='$phoneNumber')"
    }
}
