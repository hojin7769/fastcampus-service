package org.delivery.api.domain.userordermenu.service

import org.delivery.common.api.Api
import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.userordermenu.UserOrderMenuEntity
import org.delivery.db.userordermenu.UserOrderMenuRepository
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus
import org.springframework.stereotype.Service

@Service
class UserOrderMenuService(
    val userOrderMenuRepository: UserOrderMenuRepository
){

    fun getUserOrderMenu(
        userOrderId: Long
    ):List<UserOrderMenuEntity>{
        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(
            userOrderId,
            UserOrderMenuStatus.REGISTERED
        )
    }


    fun order(
        userOrderMenuEntity: UserOrderMenuEntity?
    ):UserOrderMenuEntity{
        userOrderMenuEntity
            ?.let{
                it.status = UserOrderMenuStatus.REGISTERED
                return userOrderMenuRepository.save(it)
            }?:throw ApiException(ErrorCode.NULL_POINT)
    }
}