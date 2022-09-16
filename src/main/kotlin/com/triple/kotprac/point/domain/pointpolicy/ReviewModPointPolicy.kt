package com.triple.kotprac.point.domain.pointpolicy

import com.triple.kotprac.point.domain.PointHistory

class ReviewModPointPolicy(
    private val prePointHistory: PointHistory
) : ReviewPointPolicy() {
    companion object {
        private const val ZERO = 0
        private const val PLUS_CONTENT_POINT = 1
        private const val PLUS_IMAGE_POINT = 1
        private const val MINUS_CONTENT_POINT = -1
        private const val MINUS_IMAGE_POINT = -1
    }

    override fun calculatePoint(pointHistory: PointHistory) =
        contentCal(pointHistory) + imgCal(pointHistory)

    private fun contentCal(updatedPointHistory: PointHistory) =
        if (!prePointHistory.contentExist && updatedPointHistory.contentExist)
            PLUS_CONTENT_POINT
        else if (prePointHistory.contentExist && !updatedPointHistory.contentExist)
            MINUS_CONTENT_POINT
        else ZERO

    private fun imgCal(updatedPointHistory: PointHistory) =
        if (!prePointHistory.imgExist && updatedPointHistory.imgExist)
            PLUS_IMAGE_POINT
        else if (prePointHistory.imgExist && !updatedPointHistory.imgExist)
            MINUS_IMAGE_POINT
        else ZERO
}