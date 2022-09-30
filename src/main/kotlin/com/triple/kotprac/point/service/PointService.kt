package com.triple.kotprac.point.service

import com.triple.kotprac.common.event.Events
import com.triple.kotprac.point.domain.event.PointCalculatedEvent
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import com.triple.kotprac.point.dto.PointHistoryResponse
import com.triple.kotprac.point.dto.PointRequest
import com.triple.kotprac.point.service.review.ReviewPointHistoryServiceFactory
import org.springframework.stereotype.Service

@Service
class PointService(
    private val pointHistoryRepository: PointHistoryRepository,
    private val reviewPointFactory: ReviewPointHistoryServiceFactory,
) {
    fun handleEvent(request: PointRequest): PointHistoryResponse.OnlyId {
        val pointHistory = when (request) {
            is PointRequest.Review -> reviewPointFactory.getService(request)
                .createPointHistory(request.toEntity())
        }
        Events.raise(PointCalculatedEvent(pointHistory.userId, pointHistory.point))
        return pointHistoryRepository.save(pointHistory).let { PointHistoryResponse.OnlyId.of(it) }
    }
}