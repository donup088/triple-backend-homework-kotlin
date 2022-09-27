package com.triple.kotprac.point.controller

import com.triple.kotprac.point.controller.BaseController.Companion.ok
import com.triple.kotprac.point.dto.PointRequest
import com.triple.kotprac.point.service.PointService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PointController(
    private val pointService: PointService
) {
    @PostMapping("/events")
    fun update(@RequestBody request: PointRequest) =
        pointService.handleEvent(request).ok()
}