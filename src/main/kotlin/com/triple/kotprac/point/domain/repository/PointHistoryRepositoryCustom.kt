package com.triple.kotprac.point.domain.repository

import com.triple.kotprac.point.domain.PointHistory
import org.springframework.data.domain.Pageable

interface PointHistoryRepositoryCustom {
    fun findByPlaceIdAndUserIdOrderByCreatedAtDesc(placeId: Long, userId: Long, pageable: Pageable): PointHistory?

    fun getPointSumByUserIdAndPlaceId(userId: Long, placeId: Long): Int

    fun isFirstReview(placeId: Long): Boolean
}