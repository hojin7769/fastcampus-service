/*
package org.delivery.api.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userorder.UserOrderRepository;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;


    public UserOrderEntity getUserOrderWithOutStatusWithThrow(
            Long id,
            Long userId
    ){
        return Optional.ofNullable(userOrderRepository.findAllByIdAndUserId(
                id,
                userId
        ))
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
    public UserOrderEntity getUserOrderWithThrow(
            Long id,
            Long userId
    ){
        return Optional.ofNullable(userOrderRepository.findAllByIdAndStatusAndUserId(
                id,
                UserOrderStatus.REGISTERED,
                userId
        ))
         .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }


    public List<UserOrderEntity> getUserOrderList(Long userId){
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    public List<UserOrderEntity> getUserOrderList(Long userId, List<UserOrderStatus> status){
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, status);
    }

    // 현재 진행중인 내역
    public List<UserOrderEntity> current(Long userId){
        return getUserOrderList(userId, List.of(
                UserOrderStatus.ORDER,
                UserOrderStatus.COOKING,
                UserOrderStatus.DELIVERY,
                UserOrderStatus.ACCEPT
        ));
    }

    // 과거 주문한 내역
    public List<UserOrderEntity> history(Long userId){
        return getUserOrderList(userId, List.of(
                UserOrderStatus.RECEIVE
        ));
    }


    //주문( create)
    public UserOrderEntity order(UserOrderEntity userOrderEntity){
        return Optional.ofNullable(userOrderEntity)
                .map(it ->{
                    it.setStatus(UserOrderStatus.ORDER);
                    it.setOrderedAt(LocalDateTime.now());
                    return userOrderRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    //상대 변경 메서드
    public UserOrderEntity setStatus(UserOrderEntity userOrderEntity, UserOrderStatus status){
       userOrderEntity.setStatus(status);
       return userOrderRepository.save(userOrderEntity);
    }
    //주문 확인

    public UserOrderEntity accept(UserOrderEntity userOrderEntity){
        return Optional.ofNullable(userOrderEntity)
                .map(it ->{
                    it.setAcceptedAt(LocalDateTime.now());
                    return setStatus(it, UserOrderStatus.ACCEPT);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    //조리 시작
    public UserOrderEntity cooking(UserOrderEntity userOrderEntity){
        return Optional.ofNullable(userOrderEntity)
                .map(it ->{
                    it.setCookingStartedAt(LocalDateTime.now());
                    return setStatus(it, UserOrderStatus.COOKING);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    //배달 시작
    public UserOrderEntity delivery(UserOrderEntity userOrderEntity){
        return Optional.ofNullable(userOrderEntity)
                .map(it ->{
                    it.setDeliveryStartedAt(LocalDateTime.now());
                    return setStatus(it, UserOrderStatus.DELIVERY);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    //배달 완료
    public UserOrderEntity receive(UserOrderEntity userOrderEntity){
        return Optional.ofNullable(userOrderEntity)
                .map(it ->{
                    it.setReceivedAt(LocalDateTime.now());
                    return setStatus(it, UserOrderStatus.RECEIVE);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
*/
