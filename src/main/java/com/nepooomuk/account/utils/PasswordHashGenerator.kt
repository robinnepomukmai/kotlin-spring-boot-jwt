package com.nepooomuk.account.utils

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class PasswordHashGenerator : PasswordEncoder {
    override fun encode(rawPassword: CharSequence): String {

        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12))
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword)
    }

    companion object {


        fun encodePassword(password: String): String {
            val passwordEncoder = BCryptPasswordEncoder()
            return passwordEncoder.encode(password)
        }
    }
}
