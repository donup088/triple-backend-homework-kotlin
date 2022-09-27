package com.triple.kotprac.point.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class BaseController {
    companion object {
        fun <T> T.ok(headers: HttpHeaders? = null) = ResponseEntity(this, headers, HttpStatus.OK)
    }
}