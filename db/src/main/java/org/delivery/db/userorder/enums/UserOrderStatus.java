package org.delivery.db.userorder.enums;

import lombok.Getter;

@Getter
public enum UserOrderStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    //주문,
    ORDER("주문"),
    //확인
    ACCEPT("확인"),
    // 요리중
    COOKING("요리중"),
    //배달중
    DELIVERY("배달중"),
    //완료
    RECEIVE("완료"),
    ;

    UserOrderStatus(String description){
        this.description = description;
    }
    private String description;
}
