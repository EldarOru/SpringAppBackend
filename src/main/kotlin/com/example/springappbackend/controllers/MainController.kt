package com.example.springappbackend.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class MainController {

    @GetMapping("/index")
    fun greeting(): String {
        return "index"
    }
}