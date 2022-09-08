package com.triple.kotprac.review.service

import com.triple.kotprac.common.exception.review.ReviewNotFoundException
import com.triple.kotprac.place.service.integrate.Places
import com.triple.kotprac.review.Review
import com.triple.kotprac.review.ReviewValidator
import com.triple.kotprac.review.dto.ReviewRequest
import com.triple.kotprac.review.repository.ReviewRepository
import com.triple.kotprac.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ReviewService(
        private val reviewRepository: ReviewRepository,
        private val reviewValidator: ReviewValidator,
        private val places: Places,
        private val reviewFileService: ReviewFileService
) {
    @Transactional
    fun create(request: ReviewRequest.Create, images: List<MultipartFile>?, loginUser: User) {
        val place = places.getOne(request.placeId)
        val review = Review.create(request.content, place, loginUser, reviewValidator)
        val savedReview = reviewRepository.save(review)
        reviewFileService.uploadList(savedReview, images)
    }

    @Transactional
    fun update(reviewId: Long, request: ReviewRequest.Update, images: List<MultipartFile>?, loginUser: User) {
        val review = reviewRepository.findByIdWithImage(reviewId) ?: throw ReviewNotFoundException()
        val fileIdList = reviewFileService.updateFiles(request.fileChange, review, images)
        review.update(request.content, review, reviewValidator, loginUser)
    }

    @Transactional
    fun delete(reviewId: Long, loginUser: User) {
        val review = reviewRepository.findByIdWithImage(reviewId) ?: throw ReviewNotFoundException()
        review.delete(review, loginUser, reviewValidator)

        reviewFileService.deleteByReview(review)
        reviewRepository.deleteById(reviewId)
    }
}