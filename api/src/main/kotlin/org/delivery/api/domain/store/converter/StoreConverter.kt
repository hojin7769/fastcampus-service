package org.delivery.api.domain.store.converter

import org.delivery.api.domain.store.controller.model.StoreRegisterRequest
import org.delivery.api.domain.store.controller.model.StoreResponse
import org.delivery.common.annotation.Converter
import org.delivery.db.store.StoreEntity

@Converter
class StoreConverter {

    fun toEntity(
        request : StoreRegisterRequest?
    ):StoreEntity?{
        return StoreEntity(
            name = request?.name,
            address = request?.address,
            category = request?.category,
            thumbnailUrl = request?.thumbnailUrl,
            minimumAmount = request?.minimumAmount,
            minimumDeliveryAmount = request?.minimumDeliveryAmount,
            phoneNumber = request?.phoneNumber
        )
    }


    fun toResponse(
            entity: StoreEntity?
    ): StoreResponse {
        return StoreResponse(
            id = entity?.id,
            name = entity?.name,
            address = entity?.address,
            status = entity?.status,
            category = entity?.category,
            star = entity?.star,
            thumbnailUrl = entity?.thumbnailUrl,
            minimumAmount = entity?.minimumAmount,
            minimumDeliveryAmount = entity?.minimumDeliveryAmount,
            phoneNumber = entity?.phoneNumber
        )
    }
}