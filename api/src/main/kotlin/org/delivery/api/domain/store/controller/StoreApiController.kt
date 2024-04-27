package org.delivery.api.domain.store.controller

import org.delivery.api.domain.store.business.StoreBusiness
import org.delivery.api.domain.store.controller.model.StoreResponse
import org.delivery.common.api.Api
import org.delivery.db.store.enums.StoreCategory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/store")
class StoreApiController(
    val storeBusiness: StoreBusiness
){
    @GetMapping("/search")
    fun search(
        @RequestParam(required = false)
        category:StoreCategory
    ):Api<List<StoreResponse>>{
        storeBusiness.searchCategory(category)
            .let{
                return Api.OK(body=it)
            }
    }

}