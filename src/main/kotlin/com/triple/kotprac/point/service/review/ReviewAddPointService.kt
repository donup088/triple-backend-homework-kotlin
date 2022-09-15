package com.triple.kotprac.point.service.review

import com.triple.kotprac.common.event.Events
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.event.PointCalculatedEvent
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import com.triple.kotprac.point.service.PointPlaceService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewAddPointService(
    private val pointHistoryRepository: PointHistoryRepository,
    private val pointPlaceService: PointPlaceService
) : ReviewPointHistoryService {
    override fun getPointHistoryAction() = PointHistoryAction.ADD

    @Transactional
    override fun update(pointHistory: PointHistory): PointHistory {
        val firstReview = pointPlaceService.createAndReturnIsFirstReview(pointHistory)

        val point = pointHistory.addPointCal(firstReview)
        val updatedPointHistory = pointHistory.updatePoint(point)
        Events.raise(PointCalculatedEvent(updatedPointHistory.userId, point))
        return pointHistoryRepository.save(updatedPointHistory)
    }
}