package com.nepooomuk.account.test

import com.nepooomuk.account.AccountServiceApplication
import com.nepooomuk.account.model.Role
import com.nepooomuk.account.model.User
import com.nepooomuk.account.repository.UserRepository
import lombok.`val`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.security.web.FilterChainProxy
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.context.WebApplicationContext

import java.util.Collections

import org.mockito.Mockito.`when`
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder

const val API_CLIENT = "authenticationjwtclient"
const val API_SECRET = "XY7kmzoNzl100"
const val TEST_USER_ID = "24a460e1-f20d-4556-8f66-5a82e0553886"
const val TEST_USER_EMAIL = "james.dean@nepomuk.io"
const val TEST_USER_HASHED_PASSWORD = "\$2a\$10\$Nc8S4y/KBvHI7bWmX7qfuOLU0k8W5JHJ4o1jnkaR8OZh5AaRdMXdW"
const val TEST_USER_WRONG_PASSWORD ="wrong password"
const val TEST_ROLE_ID ="ed1db5c6-a8a3-447a-a35c-9c52492785e5"
const val TEST_ROLE_NAME ="JDT"
const val TEST_ROLE_DESCRIPTION ="James Dean Team"

@SpringBootTest(classes = arrayOf(AccountServiceApplication::class))
@RunWith(SpringRunner::class)
class UserControllerTest {

    @Autowired
    private val context: WebApplicationContext? = null

    private var clientRepositoryMock: UserRepository? = null


    @Autowired
    private val springSecurityFilterChain: FilterChainProxy? = null

    private val mvc: MockMvc? = null

    @Before
    fun setup() {
        clientRepositoryMock = Mockito.mock(UserRepository::class.java)
        val mvc = MockMvcBuilders.webAppContextSetup(context!!)
                .addFilters<DefaultMockMvcBuilder>(springSecurityFilterChain!!).build()
    }

    fun createMockUser(id: String, email: String, password: String, roles: List<Role>): User {
        val markusMockUser = User()
        markusMockUser.id = id
        markusMockUser.email = email
        markusMockUser.password = password
        markusMockUser.roles = roles
        return markusMockUser
    }

    fun createMockRole(roleDescription: String, roleId: String, roleName: String): Role {
        val mockRoles = Role()
        mockRoles.description = roleDescription
        mockRoles.id = roleId
        mockRoles.roleName = roleName
        return mockRoles
    }

    fun createRoleListMock(): List<Role> {
        return listOf(createMockRole(TEST_ROLE_DESCRIPTION, TEST_ROLE_ID, TEST_ROLE_NAME))
    }

    @Test
    @Throws(Exception::class)
    fun permitUnauthorizedAccess() {
        val mvc = MockMvcBuilders.webAppContextSetup(context!!)
                .addFilters<DefaultMockMvcBuilder>(springSecurityFilterChain!!).build()

        mvc.perform(post("/api/login")
                .with(httpBasic(API_CLIENT, "falsePassword")))
                .andExpect(status().isUnauthorized)
    }

    @Test
    @Throws(Exception::class)
    fun correctHttpBasicAuthPasswordButFalseUserCredentials() {
        val mvc = MockMvcBuilders.webAppContextSetup(context!!)
                .addFilters<DefaultMockMvcBuilder>(springSecurityFilterChain!!).build()

        mvc.perform(post("/api/login")
                .with(httpBasic(API_CLIENT, API_SECRET))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isBadRequest)
    }

    @Test
    @Throws(Exception::class)
    fun successfullTokenCreationTest() {
        val mvc = MockMvcBuilders.webAppContextSetup(context!!)
                .addFilters<DefaultMockMvcBuilder>(springSecurityFilterChain!!).build()

        val mockUser = createMockUser(TEST_USER_ID, TEST_USER_EMAIL, TEST_USER_HASHED_PASSWORD, createRoleListMock())

        `when`(clientRepositoryMock!!.findByEmail(TEST_USER_EMAIL)).thenReturn(mockUser)

        val params = LinkedMultiValueMap<String, String>()
        params.add("grant_type", "password")
        params.add("username", TEST_USER_EMAIL)
        params.add("password", "test123")

        mvc.perform(post("/api/login")
                .params(params)
                .with(httpBasic(API_CLIENT, API_SECRET))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun unsuccessfullTokenCreationTest() {
        val mvc = MockMvcBuilders.webAppContextSetup(context!!)
                .addFilters<DefaultMockMvcBuilder>(springSecurityFilterChain!!).build()

        val mockUser = createMockUser(TEST_USER_ID, TEST_USER_EMAIL, TEST_USER_HASHED_PASSWORD, createRoleListMock())

        `when`(clientRepositoryMock!!.findByEmail(TEST_USER_EMAIL)).thenReturn(mockUser)

        val params = LinkedMultiValueMap<String, String>()
        params.add("grant_type", "password")
        params.add("username", TEST_USER_EMAIL)
        params.add("password", TEST_USER_WRONG_PASSWORD)

        mvc.perform(post("/api/login")
                .params(params)
                .with(httpBasic(API_CLIENT, API_SECRET))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isBadRequest)
    }
}