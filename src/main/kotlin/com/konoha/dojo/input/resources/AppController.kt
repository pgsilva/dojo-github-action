package com.konoha.dojo.input.resources

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/app")
class AppController {

    @GetMapping("/isAlive")
    fun hello(): String {
        return "I'm alive, remember with great power comes great responsibility "
    }
}