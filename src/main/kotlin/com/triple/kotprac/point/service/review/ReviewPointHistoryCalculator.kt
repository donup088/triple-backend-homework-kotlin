package com.triple.kotprac.point.service.review

import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction

interface ReviewPointHistoryCalculator {
    fun getPointHistoryAction(): PointHistoryAction
    fun calculatePoint(pointHistory: PointHistory): PointHistory
}