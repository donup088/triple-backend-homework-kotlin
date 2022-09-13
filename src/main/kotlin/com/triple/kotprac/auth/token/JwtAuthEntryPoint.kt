package com.triple.kotprac.auth.token

import com.fasterxml.jackson.databind.ObjectMapper
import com.triple.kotprac.common.dto.ErrorResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthEntryPoint : AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        val errorResponse = ErrorResponse.build(HttpServletResponse.SC_UNAUTHORIZED, authException!!)

        val mapper = ObjectMapper()
        mapper.writeValue(response?.outputStream, errorResponse)
    }
}