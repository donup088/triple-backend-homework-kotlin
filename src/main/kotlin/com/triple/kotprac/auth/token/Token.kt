package com.triple.kotprac.auth.token

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

data class Token(
        val token: String,
        val expiredAt: LocalDateTime
) {
    companion object {
        fun create(token: String, exp: Date): Token {
            return Token(token, Instant.ofEpochMilli(exp.time).atZone(ZoneId.systemDefault()).toLocalDateTime())
        }
    }
}