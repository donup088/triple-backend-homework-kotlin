package com.triple.kotprac.auth.token

import com.triple.kotprac.user.domain.User
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date


@Slf4j
@Component
class TokenProvider{

    @Value("\${jwt.access-token-props.secret}")
    lateinit var accessTokenSecret:String
    @Value("\${jwt.access-token-props.expiration-time-milli-sec}")
    lateinit var accessTokenExp:String

    private val log = LoggerFactory.getLogger(javaClass)

    fun generateAccessToken(user: User): Token {
        return generateToken(user)
    }

    fun getUserIdFromAccessToken(token: String?): String? {
        return token?.let { getClaims(it)?.body?.subject }
    }

    private fun generateToken(user: User): Token {
        val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessTokenSecret))
        val exp = Date(Date().time + accessTokenExp.toLong())
        val token = Jwts.builder()
                .setSubject(user.id.toString())
                .setExpiration(exp)
                .signWith(key)
                .compact();

        return Token.create(token, exp)
    }

    private fun getClaims(token: String): Jws<Claims>? {
        val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessTokenSecret))
        var claims: Jws<Claims>? = null
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
        } catch (e: io.jsonwebtoken.security.SecurityException) {
            log.info("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            log.info("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            log.info("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            log.info("JWT 토큰이 잘못되었습니다.")
        }
        return claims
    }
}