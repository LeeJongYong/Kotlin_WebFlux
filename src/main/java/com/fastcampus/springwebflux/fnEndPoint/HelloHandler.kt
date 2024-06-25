package com.fastcampus.springwebflux.fnEndPoint

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class HelloHandler {

    fun sayHello(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().bodyValue("Hello WebFlux.")  // 응답이 정상일 경우 bodyValue를 리턴한다
    }

}
