package com.triple.kotprac.auth.dto

import com.triple.kotprac.auth.token.Token


data class AuthResponse(val accessToken: String) {
    companion object {
        fun of(accessToken: Token): AuthResponse {
            return AuthResponse(accessToken = accessToken.token)
        }
    }
}