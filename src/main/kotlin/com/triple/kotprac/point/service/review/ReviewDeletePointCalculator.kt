package com.triple.kotprac.point.service.review

import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.pointpolicy.ReviewDeletePointPolicy
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewDeletePointCalculator(
    private val pointHistoryRepository: PointHistoryRepository
) : ReviewPointHistoryCalculator {
    override fun getPointHistoryAction() = PointHistoryAction.DELETE

    @Transactional
    override fun calculatePoint(pointHistory: PointHistory): PointHistory {
        val pointByUserGetFromPlace = pointHistoryRepository.getPointSumByUserIdAndPlaceId(
            pointHistory.userId,
            pointHistory.placeId
        )
        return pointHistory.calPoint(ReviewDeletePointPolicy(pointByUserGetFromPlace))
    }
}