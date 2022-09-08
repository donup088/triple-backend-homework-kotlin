package com.triple.kotprac.user.controller

import com.triple.kotprac.common.dto.ApiResult
import com.triple.kotprac.user.dto.UserPointResponse
import com.triple.kotprac.user.dto.UserSignupRequest
import com.triple.kotprac.user.sevice.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService
) {
    @PostMapping
    fun signup(@RequestBody @Valid request:UserSignupRequest): ResponseEntity<ApiResult<Unit>>{
        userService.signup(request)
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value()))
    }

    @GetMapping("/{userId}/point")
    fun getPoint(@PathVariable userId:Long): ResponseEntity<ApiResult<UserPointResponse>> {
        val response = userService.getPoint(userId)
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value(),response))
    }
}