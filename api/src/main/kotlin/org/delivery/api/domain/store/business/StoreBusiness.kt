package org.delivery.api.domain.store.business

import org.delivery.api.domain.store.controller.model.StoreRegisterRequest
import org.delivery.api.domain.store.controller.model.StoreResponse
import org.delivery.api.domain.store.converter.StoreConverter
import org.delivery.api.domain.store.service.StoreService
import org.delivery.common.annotation.Business
import org.delivery.db.store.enums.StoreCategory

@Business
class StoreBusiness (
    val storeService:StoreService,
    val storeConverter: StoreConverter
){
    fun register(
        storeRegisterRequest: StoreRegisterRequest?
    ):StoreResponse{
        storeConverter.toEntity(storeRegisterRequest)
            .let{
                storeService.register(it)
            }.let{
                return storeConverter.toResponse(it)
            }
    }


    fun searchCategory(
        category:StoreCategory
    ):List<StoreResponse>{
        return storeService.searchByCategory(category)
            .map{
                storeConverter.toResponse(it)
            }.toList()
    }
}