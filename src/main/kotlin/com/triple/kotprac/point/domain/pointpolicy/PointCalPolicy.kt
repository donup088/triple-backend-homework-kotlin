package com.triple.kotprac.point.domain.pointpolicy

import com.triple.kotprac.point.domain.PointHistory

interface PointCalPolicy {
    fun calculate(pointHistory: PointHistory): Int
}