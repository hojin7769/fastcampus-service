package org.delivery.api.domain.userorder.service

import org.delivery.common.error.ErrorCode
import org.delivery.common.exception.ApiException
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userorder.UserOrderRepository
import org.delivery.db.userorder.enums.UserOrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

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

    fun order(
        userOrderEntity:UserOrderEntity?
    ):UserOrderEntity{
        userOrderEntity?.let {
            it.orderedAt = LocalDateTime.now()
            it.status =UserOrderStatus.ORDER
            return userOrderRepository.save(it)
        }?:throw ApiException(ErrorCode.NULL_POINT)
    }

    fun setStatus(
        userOrderEntity: UserOrderEntity,
        status: UserOrderStatus
    ):UserOrderEntity{
        userOrderEntity.status = status
        return userOrderRepository.save(userOrderEntity)
    }


    fun accept(
        userOrderEntity:UserOrderEntity?
    ):UserOrderEntity{
        userOrderEntity?.let{
            it.acceptedAt = LocalDateTime.now()
            return setStatus(it, UserOrderStatus.ACCEPT)
        }?:throw ApiException(ErrorCode.NULL_POINT)
    }

    fun cooking(
        userOrderEntity:UserOrderEntity?
    ):UserOrderEntity{
        userOrderEntity?.let{
            it.cookingStartedAt = LocalDateTime.now()
            return setStatus(it, UserOrderStatus.COOKING)
        }?:throw ApiException(ErrorCode.NULL_POINT)
    }



    fun delivery(
        userOrderEntity:UserOrderEntity?
    ):UserOrderEntity{
        userOrderEntity?.let{
            it.deliveryStartedAt = LocalDateTime.now()
            return setStatus(it, UserOrderStatus.DELIVERY)
        }?:throw ApiException(ErrorCode.NULL_POINT)
    }


    fun receive(userOrderEntity: UserOrderEntity?): UserOrderEntity {
        userOrderEntity?.let{
            it.receivedAt = LocalDateTime.now()
            return setStatus(it, UserOrderStatus.RECEIVE)
        }?:throw ApiException(ErrorCode.NULL_POINT)
    }


}
