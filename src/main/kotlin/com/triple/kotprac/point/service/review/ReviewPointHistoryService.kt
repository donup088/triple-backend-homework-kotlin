package com.triple.kotprac.point.service.review

import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction

interface ReviewPointHistoryService {
    fun getPointHistoryAction(): PointHistoryAction
    fun createPointHistory(pointHistory: PointHistory): PointHistory
}