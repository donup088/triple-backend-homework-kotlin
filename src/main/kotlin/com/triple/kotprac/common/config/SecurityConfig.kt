package com.triple.kotprac.common.config

import com.triple.kotprac.auth.security.CustomUserDetailsService
import com.triple.kotprac.auth.token.JwtAuthEntryPoint
import com.triple.kotprac.auth.token.JwtTokenAuthenticationFilter
import com.triple.kotprac.auth.token.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val customUserDetailsService: CustomUserDetailsService,
    private val jwtAuthEntryPoint: JwtAuthEntryPoint
) : WebSecurityConfigurerAdapter() {
    @Bean
    fun tokenAuthenticationFilter(): JwtTokenAuthenticationFilter {
        return JwtTokenAuthenticationFilter(tokenProvider, customUserDetailsService)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.cors()
            .and()
            .headers().frameOptions().disable()
            .and()
            .httpBasic().disable()
            .formLogin().disable()
            .rememberMe().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
            .and()
            .authorizeRequests()
            .antMatchers("/docs/**", "/user", "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}