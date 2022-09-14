package com.triple.kotprac.point.controller

import com.triple.kotprac.common.dto.ApiResult
import com.triple.kotprac.point.dto.PointHistoryResponse
import com.triple.kotprac.point.dto.PointRequest
import com.triple.kotprac.point.service.PointService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PointController(
    private val pointService: PointService
) {
    @PostMapping("/events")
    fun update(@RequestBody request: PointRequest): ResponseEntity<ApiResult<PointHistoryResponse.OnlyId>> {
        val response = pointService.update(request)
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value(), response))
    }
}