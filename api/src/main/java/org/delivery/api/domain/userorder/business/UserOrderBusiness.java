/*
package org.delivery.api.domain.userorder.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.annotation.Business;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.delivery.api.domain.userorder.converter.UserOrderConverter;
import org.delivery.api.domain.userorder.producer.UserOrderProducer;
import org.delivery.api.domain.userorder.service.UserOrderService;
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Business
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreService storeService;
    private final StoreMenuConverter storeMenuConverter;
    private final StoreConverter storeConverter;
    private final UserOrderProducer userOrderProducer;
    private final ObjectMapper objectMapper;

    //1. 사용자, 메뉴 id
    //2. userOrder 생성
    //3. userOrderMenu 생성
    //4. 응답 생성
    public UserOrderResponse userOrder(User user , UserOrderRequest body) {

        var storeEntity = storeService.getStoreWithThrow(body.getStoreId());


        var storeMenuEntityList = body.getStoreMenuIdList().stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it))
                .collect(Collectors.toList());

        var userOrderEntity = userOrderConverter.toEntity(user, storeEntity, storeMenuEntityList);

        //주문
        var newUserOrderEntity = userOrderService.order(userOrderEntity);
        // 매핑
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> {
                    var userOrderMenuEntity = userOrderMenuConverter.toEntity(newUserOrderEntity, it);
                    return userOrderMenuEntity;
                })
                .collect(Collectors.toList());

        //주문내역 기록 남기기
        userOrderMenuEntityList.forEach(it -> userOrderMenuService.order(it));

        //비동기로 주문내역 전달
        userOrderProducer.sendOrder(newUserOrderEntity);

        return userOrderConverter.toResponse(newUserOrderEntity);
    }


    public List<UserOrderDetailResponse> current(User user) {
        var userOrderEntityList = userOrderService.current(user.getId());

        // 주문 1건씩 처리
        var userOrderDetailResponseList = userOrderEntityList.stream()
                .map(userOrderEntity -> {

                    // 사용자 주문 메뉴
                    // var userOrderMenuEntityList = userOrderMenuService.getUserOrderMenu(userOrderEntity.getId());

                    var userOrderMenuEntityList = userOrderEntity.getUserOrderMenuEntityList().stream()
                            .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                            .collect(Collectors.toList());



                    var storeMenuEntityList = userOrderMenuEntityList.stream()
                            .map(UserOrderMenuEntity::getStoreMenu).collect(Collectors.toList());

                    // 사용자가 주문한 스토어  // TODO 리팩토링 필요
                    var storeEntity = userOrderEntity.getStore();
                    //storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStore().getId());

                    return UserOrderDetailResponse.builder()
                            .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                            .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                            .storeResponse(storeConverter.toResponse(storeEntity))
                            .build();

                }).collect(Collectors.toList());

        return userOrderDetailResponseList;
    }

    public List<UserOrderDetailResponse> history(User user) {
        var userOrderEntityList = userOrderService.history(user.getId());

        // 주문 1건씩 처리
        var userOrderDetailResponseList = userOrderEntityList.stream()
                .map(userOrderEntity -> {

                    // 사용자 주문 메뉴
                    var userOrderMenuEntityList = userOrderEntity.getUserOrderMenuEntityList().stream()
                            .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                            .collect(Collectors.toList());

                    var storeMenuEntityList = userOrderMenuEntityList.stream()
                            .map(UserOrderMenuEntity::getStoreMenu)
                            .collect(Collectors.toList());

                    // 사용자가 주문한 스토어  // TODO 리팩토링 필요
                    var storeEntity = userOrderEntity.getStore();
                    //storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStore().getId());

                    return UserOrderDetailResponse.builder()
                            .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                            .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                            .storeResponse(storeConverter.toResponse(storeEntity))
                            .build();

                }).collect(Collectors.toList());

        return userOrderDetailResponseList;
    }



    public UserOrderDetailResponse read(User user, Long orderId) {
        var userOrderEntity = userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user.getId());
        // 사용자가 주문한 메뉴
        var userOrderMenuEntityList = userOrderEntity.getUserOrderMenuEntityList();

        var storeMenuEntityList = userOrderMenuEntityList.stream()
                .map(userOrderMenuEntity -> userOrderMenuEntity.getStoreMenu())
                .collect(Collectors.toList());

        // 사용자가 주문한 스토어  // TODO 리팩토링 필요
        var storeEntity = userOrderEntity.getStore();

        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                .storeResponse(storeConverter.toResponse(storeEntity))
                .build();

    }
}
*/
