package com.triple.kotprac.point.service.review

import com.triple.kotprac.common.event.Events
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.pointpolicy.ReviewAddPointPolicy
import com.triple.kotprac.point.domain.event.PointCalculatedEvent
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import com.triple.kotprac.point.service.PointPlaceService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewAddPointService(
    private val pointHistoryRepository: PointHistoryRepository,
    private val pointPlaceService: PointPlaceService
) : ReviewPointHistoryService {
    override fun getPointHistoryAction() = PointHistoryAction.ADD

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun update(pointHistory: PointHistory): PointHistory {
        val isFirstReview: Boolean = try {
            pointPlaceService.createAndReturnIsFirstReview(pointHistory)
        } catch (e: DataIntegrityViolationException) {
            false
        }
        val point = pointHistory.calPoint(ReviewAddPointPolicy(isFirstReview))
        val updatedPointHistory = pointHistory.updatePoint(point)
        Events.raise(PointCalculatedEvent(updatedPointHistory.userId, point))
        return pointHistoryRepository.save(updatedPointHistory)
    }
}