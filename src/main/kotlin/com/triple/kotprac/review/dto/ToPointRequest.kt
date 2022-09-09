package com.triple.kotprac.review.dto

import com.triple.kotprac.review.Review
import java.util.stream.Collectors

class ToPointRequest {
    data class Update(
        val type: String,
        val action: String,
        val reviewId: Long,
        val content: String?,
        val attachedPhotoIds: List<Long>,
        val userId: Long,
        val placeId: Long
    ) {
        companion object {
            fun addOrDeleteOf(review: Review, action: String, loginUserId: Long): Update {
                return Update(
                    type = "REVIEW",
                    action = action,
                    reviewId = review.id!!,
                    content = review.content,
                    attachedPhotoIds = if (review.reviewFileList.isNotEmpty()) {
                        review.reviewFileList.stream().map { file -> file.id!! }.collect(Collectors.toList())
                    } else {
                        mutableListOf()
                    },
                    userId = loginUserId,
                    placeId = review.place.id!!
                )
            }

            fun modOf(
                request: ReviewRequest.Update,
                originReview: Review,
                updatedImages: List<Long>
            ): Update {
                return Update(
                    type = "REVIEW",
                    action = "MOD",
                    reviewId = originReview.id!!,
                    content = request.content,
                    attachedPhotoIds = if (!request.fileChange && originReview.reviewFileList.isNotEmpty()) {
                        originReview.reviewFileList.stream().map { file -> file.id!! }.collect(Collectors.toList())
                    } else if (request.fileChange) {
                        updatedImages
                    } else {
                        mutableListOf()
                    },
                    userId = request.userId,
                    placeId = originReview.place.id!!
                )
            }
        }
    }
}