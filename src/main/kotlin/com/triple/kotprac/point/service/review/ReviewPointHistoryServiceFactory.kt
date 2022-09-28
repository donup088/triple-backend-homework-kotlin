package com.triple.kotprac.point.service.review

import com.triple.kotprac.point.dto.PointRequest
import org.springframework.stereotype.Component

@Component
class ReviewPointHistoryServiceFactory(
    private val calculators: List<ReviewPointHistoryCalculator>
) {
    fun getService(request: PointRequest.Review) =
        calculators.find { it.getPointHistoryAction() == request.action }
            ?: throw IllegalArgumentException()
}