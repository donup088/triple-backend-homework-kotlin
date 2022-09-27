package com.triple.kotprac.point.domain.pointpolicy

import com.triple.kotprac.point.domain.PointHistory

abstract class ReviewPointPolicy : PointCalPolicy {

    override fun calculate(pointHistory: PointHistory): Int {
        return calculatePoint(pointHistory = pointHistory)
    }

    abstract fun calculatePoint(pointHistory: PointHistory): Int
}