package org.delivery.apigw.route

import org.delivery.apigw.filter.ServiceApiPrivateFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(
    private val serviceApiPrivateFilter: ServiceApiPrivateFilter
) {

    @Bean
    fun gatewayRoute(
        builder:RouteLocatorBuilder
    ):RouteLocator{
        return builder.routes()
            .route{ spec ->
                spec.order(-1)
                spec.path( //매칭할 주소
                    "/service-api/api/**"
                ).filters { filterSpec ->
                    filterSpec.filter(
                        serviceApiPrivateFilter.apply(ServiceApiPrivateFilter.Config()) //필터 적용
                    )
                    filterSpec.rewritePath("/service-api(?<segment>/?.*)", "\${segment}")
                }.uri(
                    "http://localhost:8080" //라우팅할 주소
                )

            }
            .build()
    }

}