package com.triple.kotprac.point.service

import com.triple.kotprac.point.dto.PointHistoryResponse
import com.triple.kotprac.point.dto.PointRequest
import com.triple.kotprac.point.service.review.ReviewPointHistoryServiceFactory
import org.springframework.stereotype.Service

@Service
class PointService(
    private val reviewPointFactory: ReviewPointHistoryServiceFactory,
) {
    fun handle(request: PointRequest)=
        when (request) {
            is PointRequest.Review -> reviewPointFactory.getService(request)
                .update(request.toEntity())
                .let { pointHistory -> PointHistoryResponse.OnlyId.of(pointHistory) }
        }
}