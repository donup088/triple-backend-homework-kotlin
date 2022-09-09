package com.triple.kotprac.point.service

import com.triple.kotprac.common.event.Events
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.event.PointCalculatedEvent
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewDeletePointService(
    private val pointHistoryRepository: PointHistoryRepository
) {
    @Transactional
    fun update(updatedPointHistory: PointHistory): PointHistory {
        val point = pointHistoryRepository.getPointSumByUserIdAndPlaceId(
            updatedPointHistory.userId,
            updatedPointHistory.placeId
        )
        val pointHistory = updatedPointHistory.updatePoint(-point)
        pointHistory.delete()

        Events.raise(PointCalculatedEvent(updatedPointHistory.userId, -point))
        return pointHistoryRepository.save(pointHistory)
    }
}