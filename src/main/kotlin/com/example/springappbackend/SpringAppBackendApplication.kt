package com.example.springappbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringAppBackendApplication

fun main(args: Array<String>) {
    runApplication<SpringAppBackendApplication>(*args)
}
