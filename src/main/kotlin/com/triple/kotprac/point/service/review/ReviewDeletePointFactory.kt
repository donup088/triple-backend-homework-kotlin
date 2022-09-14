package com.triple.kotprac.point.service.review

import com.triple.kotprac.common.event.Events
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.event.PointCalculatedEvent
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewDeletePointFactory(
    private val pointHistoryRepository: PointHistoryRepository
) : ReviewPointHistoryService {
    override fun getPointHistoryAction() = PointHistoryAction.DELETE

    @Transactional
    override fun update(pointHistory: PointHistory): PointHistory {
        val point = pointHistoryRepository.getPointSumByUserIdAndPlaceId(
            pointHistory.userId,
            pointHistory.placeId
        )
        val updatedPointHistory = pointHistory.updatePoint(-point)
        updatedPointHistory.delete()

        Events.raise(PointCalculatedEvent(updatedPointHistory.userId, -point))
        return pointHistoryRepository.save(updatedPointHistory)
    }
}