package com.triple.kotprac.point.service.review

import com.triple.kotprac.common.exception.pointHistory.PointHistoryNotFoundException
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.pointpolicy.ReviewModPointPolicy
import com.triple.kotprac.point.domain.repository.PointHistoryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewModPointService(
    private val pointHistoryRepository: PointHistoryRepository
) : ReviewPointHistoryService {
    override fun getPointHistoryAction() = PointHistoryAction.MOD

    @Transactional
    override fun createPointHistory(pointHistory: PointHistory): PointHistory {
        val prePointHistory =
            pointHistoryRepository.findByPlaceIdAndUserIdOrderByCreatedAtDesc(
                pointHistory.placeId,
                pointHistory.userId,
                PageRequest.of(0, 1)
            ) ?: throw PointHistoryNotFoundException()
        return pointHistory.calPoint(ReviewModPointPolicy(prePointHistory))
    }
}