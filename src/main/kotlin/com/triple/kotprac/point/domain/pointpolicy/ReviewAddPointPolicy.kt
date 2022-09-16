package com.triple.kotprac.point.domain.pointpolicy

import com.triple.kotprac.point.domain.PointHistory

class ReviewAddPointPolicy(
    private val isFirstReview: Boolean,
) : ReviewPointPolicy() {
    companion object {
        private const val ZERO = 0
        private const val CONTENT_POINT = 1
        private const val IMAGE_POINT = 1
        private const val FIRST_REVIEW_POINT = 1
    }

    override fun calculatePoint(pointHistory: PointHistory) =
        firstReviewCal(isFirstReview) + contentCal(pointHistory) + imgCal(pointHistory)

    private fun firstReviewCal(isFirstReview: Boolean) =
        if (isFirstReview) {
            FIRST_REVIEW_POINT
        } else {
            ZERO
        }

    private fun contentCal(pointHistory: PointHistory) =
        if (pointHistory.contentExist) {
            CONTENT_POINT
        } else {
            ZERO
        }

    private fun imgCal(pointHistory: PointHistory) =
        if (pointHistory.imgExist) {
            IMAGE_POINT
        } else {
            ZERO
        }
}