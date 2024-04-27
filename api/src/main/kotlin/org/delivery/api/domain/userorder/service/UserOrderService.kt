package org.delivery.api.domain.userorder.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userorder.UserOrderRepository
import org.delivery.db.userorder.enums.UserOrderStatus
import org.springframework.stereotype.Service

@Service
class UserOrderService(
    val userOrderRepository: UserOrderRepository
) {
    fun getUserOrderWithOutStatusWithThrow(
        id: Long?,
        userId: Long?
    ): UserOrderEntity {
        return userOrderRepository.findAllByIdAndUserId(
            id = id,
            userId = userId
        ) ?: throw ApiException(ErrorCode.NULL_POINT)

    }


    fun getUserOrderWithThrow(
        id: Long?,
        userId: Long?
    ): UserOrderEntity {
        userOrderRepository.findAllByIdAndStatusAndUserId(
            id = id,
            status = UserOrderStatus.REGISTERED,
            userId = userId
        ).let {
            return it ?: throw ApiException(ErrorCode.NULL_POINT)
        }
    }

    fun getUserOrderList(
        userId: Long?
    ): List<UserOrderEntity> {
        userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(
            userId = userId,
            status = UserOrderStatus.REGISTERED
        ).let {
            return it
        }
    }

    fun getUserOrderList(
        userId: Long?,
        status: List<UserOrderStatus>
    ): List<UserOrderEntity> {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(
            userId = userId,
            status = status
        )
    }


    fun current(
        userId: Long?
    ): List<UserOrderEntity> {
        return getUserOrderList(
            userId = userId,
            mutableListOf(
                UserOrderStatus.ORDER,
                UserOrderStatus.COOKING,
                UserOrderStatus.DELIVERY,
                UserOrderStatus.ACCEPT
            )
        )
    }


    fun history(
        userId: Long?
    ): List<UserOrderEntity> {
        return getUserOrderList(
            userId, listOf(
                UserOrderStatus.RECEIVE
            )
        )
    }


}
