package com.fastcampus.springwebflux.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.atomic.AtomicInteger

data class Book(
    val id: Int,
    val name: String,
    val price: Int
)

@Service
class BookService {

    private final val nextId = AtomicInteger(0)

    private val books : MutableList<Book> = mutableListOf(
        Book(id = nextId.incrementAndGet(), name = "Kotlin Action", price = 30000 ),
        Book(id = nextId.incrementAndGet(), name = "Http Guide", price = 40000 ),
    )

    fun getAll() : Flux<Book> {
        return Flux.fromIterable(books)
    }

    fun get(id: Int): Mono<Book> {
        return Mono.justOrEmpty(books.find { it.id == id })
    }

    fun add(request: Map<String, Any>): Mono<Book> {
        return Mono.just(request)
            .map { map ->
                val book = Book(
                    id = nextId.incrementAndGet(),
                    name = map["name"].toString(),
                    price = map["price"] as Int,
                )
                books.add(book)
                book
            }
    }

    fun delete(id: Int): Mono<Void> {
        return Mono.justOrEmpty(books.find { it.id == id })
            .map { books.remove(it) }
            .then()
    }

}