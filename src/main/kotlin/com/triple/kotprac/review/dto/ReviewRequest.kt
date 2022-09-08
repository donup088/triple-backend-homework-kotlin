package com.triple.kotprac.review.dto

class ReviewRequest {
    data class Create(val content: String, val placeId: Long)

    data class Update(val content: String, val fileChange: Boolean)
}