package com.triple.kotprac.auth.token

import com.triple.kotprac.auth.security.CustomUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenAuthenticationFilter(
        private val tokenProvider: TokenProvider,
        private val customUSerDetailsService: CustomUserDetailsService,
) : OncePerRequestFilter() {
    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER = "Bearer"
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = getAccessTokenFromRequest(request)
        if (StringUtils.hasText(token)) {
            val userId = tokenProvider.getUserIdFromAccessToken(token)
            val userDetails = customUSerDetailsService.loadUserByUsername(userId)
            val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

            SecurityContextHolder.getContext().authentication = authenticationToken
        }
        filterChain.doFilter(request, response)
    }

    private fun getAccessTokenFromRequest(request: HttpServletRequest): String? {
        val token = request.getHeader(AUTHORIZATION_HEADER)
        if (StringUtils.hasText(token) && token.startsWith(BEARER)) {
            return token.substring(7)
        }
        return null
    }
}