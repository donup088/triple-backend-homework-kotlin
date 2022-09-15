package com.triple.kotprac.point.domain

import com.triple.kotprac.common.entity.BaseTimeEntity


class PointPlace(
    val placeId: Long,
    var firstReviewId: Long,
) : BaseTimeEntity() {
    fun update(firstReviewId: Long) {
        this.firstReviewId = firstReviewId
    }

    companion object {
        fun create(firstReviewId: Long, placeId: Long): PointPlace {
            return PointPlace(firstReviewId = firstReviewId, placeId = placeId)
        }
    }
}