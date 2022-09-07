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
    fun createValidate(place: Place, loginUser: User) {
        if (reviewRepository.findByPlaceIdAndUserId(place.id!!, loginUser.id!!) != null) {
            throw AlreadyWroteReviewException()
        }
    }

    fun updateValidate(originReview: Review, loginUser: User) {
        isMineValidate(originReview, loginUser)
    }

    fun deleteValidate(deleteReview: Review, loginUser: User) {
        isMineValidate(deleteReview, loginUser)
    }

    private fun isMineValidate(originReview: Review, loginUser: User) {
        if (originReview.user.id?.equals(loginUser.id) == false) {
            throw PermissionException()
        }
    }
}