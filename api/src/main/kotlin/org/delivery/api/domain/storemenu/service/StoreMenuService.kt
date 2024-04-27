package org.delivery.api.domain.storemenu.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.storemenu.StoreMenuRepository
import org.delivery.db.storemenu.enums.StoreMenuStatus
import org.springframework.stereotype.Service

@Service
class StoreMenuService(
    val storeMenuRepository: StoreMenuRepository
) {

    fun getStoreMenuWithThrow(
        id:Long
    ): StoreMenuEntity{
        storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id,StoreMenuStatus.REGISTER)
            ?.let{
                return it
            }?: throw ApiException(ErrorCode.NULL_POINT)
    }

    fun getStoreMenuByStoreId(
        storeId:Long
    ):List<StoreMenuEntity> {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(
            storeId,
            StoreMenuStatus.REGISTER
        )
    }


    fun register(
        storeMenuEntity: StoreMenuEntity?
    ): StoreMenuEntity {
        storeMenuEntity?.let{
            it.status = StoreMenuStatus.REGISTER
            return storeMenuRepository.save(it)
        }?: throw ApiException(ErrorCode.NULL_POINT)
    }
}