package org.delivery.api.domain.store.controller

import org.delivery.api.domain.store.business.StoreBusiness
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest
import org.delivery.api.domain.store.controller.model.StoreResponse
import org.delivery.common.api.Api
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/open-api/store")
class StoreOpenApiController (
    val storeBusiness: StoreBusiness
){

    @PostMapping("/register")
    fun register(
        @Valid
        @RequestBody request:Api<StoreRegisterRequest>
    )
    :Api<StoreResponse>{

        storeBusiness.register(request.body)
            .let{
                return Api.OK(body=it)
            }
    }
}