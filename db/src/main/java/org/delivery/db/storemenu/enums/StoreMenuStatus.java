package org.delivery.db.storemenu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreMenuStatus {

    REGISTER("등록"),
    UNREGISTER("해지"),
    ;

    private String description;
}
