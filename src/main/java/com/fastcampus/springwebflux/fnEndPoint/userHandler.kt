package com.fastcampus.springwebflux.fnEndPoint

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

data class User(val id: Long, val email: String)

@Component
class UserHandler {

    val users = listOf(
        User(1, "user1@example.com"),
        User(2, "user2@example.com"),
        User(3, "user3@example.com"),
        User(4, "user4@example.com"),
    )

    fun getUser(req: ServerRequest): Mono<ServerResponse> =
        users.find { req.pathVariable("id").toLong() == it.id}
            ?.let{
                ServerResponse.ok().bodyValue(it)
            } ?: ServerResponse.notFound().build()

    fun getAll(req: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().bodyValue(users)
}
