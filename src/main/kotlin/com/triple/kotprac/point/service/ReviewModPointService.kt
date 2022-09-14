package com.triple.kotprac.point.service

import com.triple.kotprac.common.event.Events
import com.triple.kotprac.common.exception.pointHistory.PointHistoryNotFoundException
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.event.PointCalculatedEvent
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewModPointService(
    private val pointHistoryRepository: PointHistoryRepository
) {
    @Transactional
    fun update(updatedPointHistory: PointHistory): PointHistory {
        val prePointHistory =
            pointHistoryRepository.findByPlaceIdAndUserIdOrderByCreatedAtDesc(
                updatedPointHistory.placeId,
                updatedPointHistory.userId,
                PageRequest.of(0, 1)
            ) ?: throw PointHistoryNotFoundException()
        val point = updatedPointHistory.modPointCal(prePointHistory)
        if (point != 0) {
            val pointHistory = updatedPointHistory.updatePoint(point)
            Events.raise(PointCalculatedEvent(updatedPointHistory.userId, point))
            return pointHistoryRepository.save(pointHistory)
        }
        return prePointHistory.update(updatedPointHistory.contentLen, updatedPointHistory.imgCount)
    }
}