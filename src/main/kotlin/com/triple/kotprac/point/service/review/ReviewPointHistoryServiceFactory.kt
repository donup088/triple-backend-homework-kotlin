package com.triple.kotprac.point.service.review

import com.triple.kotprac.point.dto.PointRequest
import org.springframework.stereotype.Component

@Component
class ReviewPointHistoryServiceFactory(
    private val reviewPointHistoryServices: List<ReviewPointHistoryService>
) {
    fun getService(request: PointRequest.Review) =
        reviewPointHistoryServices.find { it.getPointHistoryAction() == request.action }
            ?: throw IllegalArgumentException()
}