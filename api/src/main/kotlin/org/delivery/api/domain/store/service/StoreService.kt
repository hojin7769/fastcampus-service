package org.delivery.api.domain.store.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.store.StoreEntity
import org.delivery.db.store.StoreRepository
import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.stereotype.Service

/**
 * @project Mobile_Project
 * @package org.delivery.api.domain.store.service
 * @author hojin
 * @date 2024-04-26
 * @explanation
 * @version 1.0
 */
@Service
class StoreService (
        val storeRepository: StoreRepository
){

    fun getStoreWithThrow(
            id:Long?,
    ): StoreEntity {
      storeRepository.findFirstByIdAndStatusOrderByIdDesc(id,StoreStatus.REGISTERED)
                .let{
                    return it ?: throw ApiException(ErrorCode.NULL_POINT)
                }
    }


    fun register(
            storeEntity: StoreEntity?
    ):StoreEntity{
        val newStoreEntity = storeEntity.let{
            it?.star = 0.0
            it?.status = StoreStatus.REGISTERED
            storeRepository.save(it?:throw ApiException(ErrorCode.NULL_POINT))
        }

        return newStoreEntity
    }

    fun searchByCategory(
            category: StoreCategory?
    ):List<StoreEntity>{
        storeRepository.findAllByStatusAndCategoryOrderByStarDesc(
                StoreStatus.REGISTERED,category
        ).let{
            return it
        }
    }
}