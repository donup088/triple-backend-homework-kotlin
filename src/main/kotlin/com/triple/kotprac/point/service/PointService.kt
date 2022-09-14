package com.triple.kotprac.point.service

import com.triple.kotprac.point.domain.PointHistoryType
import com.triple.kotprac.point.dto.PointHistoryResponse
import com.triple.kotprac.point.dto.PointRequest
import com.triple.kotprac.point.service.review.ReviewPointHistoryServiceFactory
import org.springframework.stereotype.Service

@Service
class PointService(
    private val reviewPointFactory: ReviewPointHistoryServiceFactory
) {
    fun update(request: PointRequest): PointHistoryResponse.OnlyId {
        when (request.type) {
            PointHistoryType.REVIEW -> {
                request as PointRequest.Update
                return reviewPointFactory.getService(request.action)!!
                    .update(request.toEntity())
                    .let { pointHistory -> PointHistoryResponse.OnlyId.of(pointHistory) }
            }
        }
    }
}