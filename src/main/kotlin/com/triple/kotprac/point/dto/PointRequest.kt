package com.triple.kotprac.point.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.PointHistoryType

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes(
    value = [
        JsonSubTypes.Type(value = PointRequest.Review::class, name = REVIEW_VALUE),
    ]
)
sealed class PointRequest {
    abstract val type: PointHistoryType

    data class Review(
        override val type: PointHistoryType = PointHistoryType.REVIEW,
        val action: PointHistoryAction,
        val reviewId: Long,
        val userId: Long,
        val placeId: Long,
        val attachedPhotoIds: List<String>,
        val content: String,
    ) : PointRequest() {
        fun toEntity(): PointHistory {
            return PointHistory(
                type = type,
                action = action,
                userId = userId,
                contentLen = content.trim().length,
                imgCount = attachedPhotoIds.size,
                reviewId = reviewId,
                placeId = placeId,
            )
        }
    }
}