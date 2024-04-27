//package org.delivery.api.domain.storemenu.business
//
//import org.delivery.api.domain.store.service.StoreService
//import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest
//import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse
//import org.delivery.api.domain.storemenu.converter.StoreMenuConverter
//import org.delivery.api.domain.storemenu.service.StoreMenuService
//import org.delivery.common.annotation.Business
//
//@Business
//class StoreMenuBusiness(
//    val storeMenuService: StoreMenuService,
//    val storeMenuConverter: StoreMenuConverter,
//    val storeService: StoreService
//) {
//
//    fun register(
//        request: StoreMenuRegisterRequest?
//    ): StoreMenuResponse {
//        storeService.getStoreWithThrow(request?.storeId)
//            ?.let {
//                storeMenuConverter.toEntity(
//                    request = request,
//                    storeEntity = it
//                )
//            }.let{
//                return storeMenuConverter.toResponse(
//                    storeMenuService.register(it)
//                )
//            }
//    }
//}