package com.triple.kotprac.point.service.review

import com.triple.kotprac.point.domain.PointHistoryAction
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils

@Component
class ReviewPointHistoryServiceFactory(
    reviewPointHistoryServices: List<ReviewPointHistoryService>
) {
    private val reviewPointHistoryServicesMap: HashMap<PointHistoryAction, ReviewPointHistoryService> = hashMapOf()

    init {
        if (CollectionUtils.isEmpty(reviewPointHistoryServices)) {
            throw IllegalArgumentException()
        }
        for (pointHistoryService in reviewPointHistoryServices) {
            reviewPointHistoryServicesMap[pointHistoryService.getPointHistoryAction()] = pointHistoryService
        }
    }

    fun getService(pointHistoryAction: PointHistoryAction) = reviewPointHistoryServicesMap[pointHistoryAction]
}