package com.fastcampus.springwebflux.fnEndPoint

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
open class Router {

    @Bean
    open fun helloRouter(handler: HelloHandler): RouterFunction<ServerResponse> =
        route()
            .GET("/", handler::sayHello)    // 기본 루트로 인입되면 sayHello 함수 실행
            .build()

    @Bean
    open fun userRouter(handler: UserHandler): RouterFunction<ServerResponse> =
//        route()
//            .GET("/users/{id}", handler::getUser)
//            .GET("/users", handler::getAll)
//            .build()

        // 공통된 route는 그룹화하여 사용가능하다.
        router {
            "/users".nest {
                GET("/{id}", handler::getUser)
                GET("", handler::getAll)
            }
        }
}