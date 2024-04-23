package org.delivery.api.domain.userorder.business

import com.fasterxml.jackson.databind.ObjectMapper
import org.delivery.api.common.Log
import org.delivery.api.domain.store.converter.StoreConverter
import org.delivery.api.domain.store.service.StoreService
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter
import org.delivery.api.domain.storemenu.service.StoreMenuService
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse
import org.delivery.api.domain.userorder.converter.UserOrderConverter
import org.delivery.api.domain.userorder.producer.UserOrderProducer
import org.delivery.api.domain.userorder.service.UserOrderService
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService
import org.delivery.common.annotation.Business

@Business
class UserOrderBusiness(
    private val userOrderService: UserOrderService,
    private val storeMenuService: StoreMenuService,
    private val userOrderConverter: UserOrderConverter,
    private val userOrderMenuConverter: UserOrderMenuConverter,
    private val userOrderMenuService: UserOrderMenuService,
    private val storeService: StoreService,
    private val storeMenuConverter: StoreMenuConverter,
    private val storeConverter: StoreConverter,
    private val userOrderProducer: UserOrderProducer,
    private val objectMapper: ObjectMapper
) {
    companion object : Log


    /**
     * 주문하기
     * @param user 사용자
     * @param body 주문 요청
     * @return 주문 응답
     */
    fun userOrder(
        user:User?,
        body: UserOrderRequest?
    ):UserOrderResponse{

        // 가게찾기
        val storeEntity = storeService.getStoreWithThrow(body?.storeId)

        // 주문한 메뉴 찾기
        val storeMenuEntityList = body?.storeMenuIdList
            ?.mapNotNull { storeMenuService.getStoreMenuWithThrow(it)}
            ?.toList()


        val userOrderEntity = userOrderConverter
            .toEntity(
                user = user,
                storeEntity = storeEntity,
                storeMenuEntityList = storeMenuEntityList
            ).run {
                userOrderService.order(this)
            }

        //order menu list 생성
        val userOrderMenuEntityList = storeMenuEntityList
            ?.map { userOrderMenuConverter.toEntity(userOrderEntity, it) }
            ?.toList()

        //주문내역 기록 남기기
        userOrderMenuEntityList?.forEach{ userOrderMenuService.order(it) }

        //비동기로 주문내역 전달
        userOrderProducer.sendOrder(userOrderEntity)

        return userOrderConverter.toResponse(userOrderEntity)
    }


    fun current(
        user: User?
    ): List<UserOrderDetailResponse>? {

        val userOrderEntityList = userOrderService.current(user?.id)

        val userOrderDetailResponseList = userOrderEntityList
            ?.map{

            }


        return null

    }

}