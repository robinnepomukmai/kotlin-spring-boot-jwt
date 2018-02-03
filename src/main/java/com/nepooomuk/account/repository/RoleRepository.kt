package com.nepooomuk.account.repository

import com.nepooomuk.account.model.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, String>
