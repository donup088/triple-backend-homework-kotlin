package com.triple.kotprac.point.dto

import com.triple.kotprac.point.domain.PointHistory

class PointHistoryResponse {
    data class OnlyId(
        val pointHistoryId: Long
    ) {
        companion object {
            fun of(pointHistory: PointHistory): OnlyId {
                return OnlyId(pointHistoryId = pointHistory.id!!)
            }
        }
    }
}