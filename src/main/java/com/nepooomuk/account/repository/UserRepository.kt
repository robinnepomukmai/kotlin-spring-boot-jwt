package com.nepooomuk.account.repository

import com.nepooomuk.account.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {
    fun findByEmail(email: String): User
}
