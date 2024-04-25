package org.delivery.api.domain.store.converter

import org.delivery.api.domain.store.controller.model.StoreRegisterRequest
import org.delivery.common.annotation.Converter

@Converter
class StoreConverter {

    fun toEntity(
        request : StoreRegisterRequest?
    ){
        request
    }
}