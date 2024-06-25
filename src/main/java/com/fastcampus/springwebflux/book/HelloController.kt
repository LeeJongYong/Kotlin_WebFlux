package com.fastcampus.springwebflux.book

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("/hello")
    fun hello() = "Hello WebFlux Controller of MVC Pattern"

}