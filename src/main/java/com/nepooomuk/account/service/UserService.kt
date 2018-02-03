package com.nepooomuk.account.service

import com.nepooomuk.account.model.User
import com.nepooomuk.account.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

import java.util.ArrayList

@Component
class UserService @Autowired
constructor(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(s: String): UserDetails {
        val user = userRepository.findByEmail(s)
                ?: throw UsernameNotFoundException(String.format("The username %s doesn't exist", s))

        val authorities = ArrayList<GrantedAuthority>()
        user.roles!!.forEach { role -> authorities.add(SimpleGrantedAuthority(role.roleName)) }

        return org.springframework.security.core.userdetails.User(user.email, user.password, authorities)
    }
}
