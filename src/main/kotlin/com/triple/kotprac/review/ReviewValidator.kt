package com.triple.kotprac.review

import com.triple.kotprac.common.exception.AlreadyWroteReviewException
import com.triple.kotprac.common.exception.PermissionException
import com.triple.kotprac.place.domain.Place
import com.triple.kotprac.review.repository.ReviewRepository
import com.triple.kotprac.user.domain.User
import org.springframework.stereotype.Component

@Component
class ReviewValidator(
    private val reviewRepository: ReviewRepository
) {
    fun createValidate(place: Place, userId: Long) {
        if (reviewRepository.findByPlaceIdAndUserId(place.id!!, userId) != null) {
            throw AlreadyWroteReviewException()
        }
    }

    fun updateValidate(originReview: Review, loginUserId: Long) {
        isMineValidate(originReview, loginUserId)
    }

    fun deleteValidate(deleteReview: Review, loginUser: User) {
        isMineValidate(deleteReview, loginUser.id!!)
    }

    private fun isMineValidate(originReview: Review, loginUserId: Long) {
        if (originReview.userId != loginUserId) {
            throw PermissionException()
        }
    }
}