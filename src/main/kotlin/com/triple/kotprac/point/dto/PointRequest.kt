package com.triple.kotprac.point.dto

import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.PointHistoryType
import org.jetbrains.annotations.NotNull

class PointRequest {
    data class Update(
            val type: PointHistoryType,
            val action: PointHistoryAction,
            val reviewId: Long,
            val userId: Long,
            val placeId: Long,
            val attachedPhotoIds: List<String>,
            val content:String
    ){
        fun toEntity():PointHistory{
            return PointHistory(
                    type=type,
                    action=action,
                    userId = userId,
                    contentExist = content.trim().isNotEmpty(),
                    imgExist = attachedPhotoIds.isNotEmpty(),
                    reviewId = reviewId,
                    placeId = placeId,
            )
        }
    }
}