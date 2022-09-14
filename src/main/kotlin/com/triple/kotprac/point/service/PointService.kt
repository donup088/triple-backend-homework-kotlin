package com.triple.kotprac.point.service

import com.triple.kotprac.point.dto.PointHistoryResponse
import com.triple.kotprac.point.dto.PointRequest
import com.triple.kotprac.point.service.review.ReviewPointHistoryServiceFactory
import org.springframework.stereotype.Service

@Service
class PointService(
    private val reviewPointFactory: ReviewPointHistoryServiceFactory
) {

    fun update(request: PointRequest.Update): PointHistoryResponse.OnlyId {
        val pointHistoryRequest = request.toEntity()
        return reviewPointFactory.getService(request.action)!!.update(pointHistoryRequest)
            .let { pointHistory -> PointHistoryResponse.OnlyId.of(pointHistory) }
    }
}