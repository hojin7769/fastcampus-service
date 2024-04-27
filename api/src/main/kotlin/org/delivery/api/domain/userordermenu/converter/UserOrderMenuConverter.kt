package org.delivery.api.domain.userordermenu.converter

import org.delivery.common.annotation.Converter
import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userordermenu.UserOrderMenuEntity

@Converter
class UserOrderMenuConverter {

    fun toEntity(
        userOrderEntity: UserOrderEntity?,
        storeMenuEntity: StoreMenuEntity?
    ):UserOrderMenuEntity{
        return UserOrderMenuEntity(
            userOrder = userOrderEntity,
            storeMenu = storeMenuEntity
        )
    }
}