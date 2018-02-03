package com.nepooomuk.account.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class UserController {

    @GetMapping("/api/user/{user}")
    fun getUser(@PathVariable("user") user: String): ResponseEntity<String> {
        return ResponseEntity("Ich bin User: " + user, HttpStatus.OK)
    }
}
