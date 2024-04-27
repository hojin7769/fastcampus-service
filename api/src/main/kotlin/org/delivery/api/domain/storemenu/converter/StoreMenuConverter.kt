package org.delivery.api.domain.storemenu.converter

import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse
import org.delivery.common.annotation.Converter
import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.StoreEntity
import org.delivery.db.storemenu.StoreMenuEntity

@Converter
class StoreMenuConverter {

    fun toEntity(
        request: StoreMenuRegisterRequest?,
        storeEntity: StoreEntity?
    ): StoreMenuEntity {

        request?.let {
            return StoreMenuEntity(
                store = storeEntity,
                name = it.name,
                amount = it.amount,
                thumbnailUrl = it.thumbnailUrl,
            )
        }?: throw ApiException(ErrorCode.NULL_POINT)
    }

    fun toResponse(
        entity: StoreMenuEntity?
    ): StoreMenuResponse{

        entity?.let{
            return StoreMenuResponse(
                id = it.id,
                storeId = it.store?.id,
                name = it.name,
                amount = it.amount,
                status = it.status,
                thumbnailUrl = it.thumbnailUrl,
                likeCount = it.likeCount,
                sequence = it.sequence
            )
        }?: throw ApiException(ErrorCode.NULL_POINT)
    }


    fun toResponse(
        list:List<StoreMenuEntity>
    ):List<StoreMenuResponse>{
        return list.map {
            toResponse(it)
        }.toList()
    }
}
