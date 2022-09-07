package com.triple.kotprac.auth.controller

import com.triple.kotprac.auth.dto.AuthRequest
import com.triple.kotprac.auth.dto.AuthResponse
import com.triple.kotprac.auth.service.AuthService
import com.triple.kotprac.common.dto.ApiResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
        private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<ApiResult<AuthResponse>> {
        val response = authService.login(request)
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value(),response))
    }

}