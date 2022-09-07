package com.triple.kotprac.auth.service

import com.triple.kotprac.auth.dto.AuthRequest
import com.triple.kotprac.auth.dto.AuthResponse
import com.triple.kotprac.auth.token.TokenProvider
import com.triple.kotprac.user.sevice.integrate.Users
import org.springframework.stereotype.Service

@Service
class AuthService(
        private val users: Users,
        private val provider:TokenProvider
) {
    fun login(request: AuthRequest): AuthResponse {
        val user = users.getOneByNickname(request.nickname)
        return AuthResponse.of(provider.generateAccessToken(user))
    }
}