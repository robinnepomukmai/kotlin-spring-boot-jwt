package com.nepooomuk.account

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
open class AccountServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(AccountServiceApplication::class.java, *args)
}