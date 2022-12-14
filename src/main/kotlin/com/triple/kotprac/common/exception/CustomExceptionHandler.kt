package com.triple.kotprac.common.exception

import com.triple.kotprac.common.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    fun businessExceptionHandler(ex: BusinessException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.build(HttpServletResponse.SC_BAD_REQUEST, ex)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PermissionException::class)
    fun permissionExceptionHandler(ex: PermissionException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.build(HttpServletResponse.SC_FORBIDDEN, ex)
        return ResponseEntity(response, HttpStatus.FORBIDDEN)
    }
}