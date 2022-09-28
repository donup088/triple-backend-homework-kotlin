package com.triple.kotprac.point.domain.pointpolicy

import com.triple.kotprac.point.domain.PointHistory


class ReviewDeletePointPolicy(
    private val pointByUserGetFromPlace: Int
) : ReviewPointPolicy() {
    override fun calculatePoint(pointHistory: PointHistory) = pointByUserGetFromPlace * MINUS

    companion object {
        private const val MINUS = -1
    }
}