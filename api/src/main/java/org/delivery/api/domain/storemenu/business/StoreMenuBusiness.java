package org.delivery.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.Business;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;


    public StoreMenuResponse register (StoreMenuRegisterRequest request){
        var storeMenu = storeMenuConverter.toEntity(request);
        var savedStoreMenu = storeMenuService.register(storeMenu);
        return storeMenuConverter.toResponse(savedStoreMenu);
    }

    public List<StoreMenuResponse> search(Long storeId){
        var storeMenus = storeMenuService.getStoreMenuByStoreId(storeId);

        return storeMenus.stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }
}
