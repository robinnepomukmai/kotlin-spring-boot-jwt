package com.nepooomuk.account.config

import java.util.Arrays

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfig @Autowired
constructor(private var tokenStore: TokenStore, private var accessTokenConverter: JwtAccessTokenConverter, private var authenticationManager: AuthenticationManager) : AuthorizationServerConfigurerAdapter() {

    @Value("\${security.jwt.client-id}")
    private val clientId: String? = null

    @Value("\${security.jwt.client-secret}")
    private val clientSecret: String? = null

    @Value("\${security.jwt.grant-type}")
    private val grantType: String? = null

    @Value("\${security.jwt.scope-read}")
    private val scopeRead: String? = null

    @Value("\${security.jwt.scope-write}")
    private val scopeWrite = "write"

    @Value("\${security.jwt.resource-ids}")
    private val resourceIds: String? = null

    @Throws(Exception::class)
    override fun configure(configurer: ClientDetailsServiceConfigurer?) {
        configurer!!
                .inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes(grantType!!)
                .scopes(scopeRead, scopeWrite)
                .resourceIds(resourceIds!!)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        val enhancerChain = TokenEnhancerChain()
        enhancerChain.setTokenEnhancers(Arrays.asList<TokenEnhancer>(accessTokenConverter))
        endpoints!!.tokenStore(tokenStore)
                .pathMapping("/oauth/token", "/api/login")
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager)
    }

}
