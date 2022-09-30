package com.triple.kotprac.point.service.review

import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.pointpolicy.ReviewAddPointPolicy
import com.triple.kotprac.point.service.PointPlaceService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewAddPointService(
    private val pointPlaceService: PointPlaceService
) : ReviewPointHistoryService {
    override fun getPointHistoryAction() = PointHistoryAction.ADD

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun createPointHistory(pointHistory: PointHistory): PointHistory {
        val isFirstReview: Boolean = try {
            pointPlaceService.createAndReturnIsFirstReview(pointHistory)
        } catch (e: DataIntegrityViolationException) {
            false
        }
        return pointHistory.calPoint(ReviewAddPointPolicy(isFirstReview))
    }
}