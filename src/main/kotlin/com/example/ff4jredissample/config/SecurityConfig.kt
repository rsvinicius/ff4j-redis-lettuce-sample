package com.example.ff4jredissample.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


private const val ROLE_ADMIN = "ADMIN"

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Value("\${ff4j.username}") private val username: String,
    @Value("\${ff4j.password}") private val password: String
) {
    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        val user = User
            .withUsername(username)
            .password(encoder.encode(password))
            .roles(ROLE_ADMIN)
            .build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeRequests()
            .mvcMatchers("/api/**").permitAll()
            .mvcMatchers("/ff4j-web-console").hasRole(ROLE_ADMIN)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable().headers().frameOptions().disable()
        return http.build()
    }
}