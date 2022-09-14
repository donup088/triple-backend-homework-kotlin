package com.triple.kotprac.point.service

import com.triple.kotprac.common.event.Events
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.event.PointCalculatedEvent
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewAddPointService(
    private val pointHistoryRepository: PointHistoryRepository,
    private val pointPlaceService: PointPlaceService
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun update(updatedPointHistory: PointHistory): PointHistory {
        val isFirstReview: Boolean = try {
            pointPlaceService.createAndReturnIsFirstReview(updatedPointHistory)
        } catch (e: DataIntegrityViolationException) {
            false
        }
        val point = updatedPointHistory.addPointCal(isFirstReview)
        val pointHistory = updatedPointHistory.updatePoint(point)
        Events.raise(PointCalculatedEvent(updatedPointHistory.userId, point))
        return pointHistoryRepository.save(pointHistory)
    }
}