package com.triple.kotprac.review.service

import com.triple.kotprac.common.exception.review.ReviewNotFoundException
import com.triple.kotprac.place.service.integrate.Places
import com.triple.kotprac.review.Review
import com.triple.kotprac.review.ReviewValidator
import com.triple.kotprac.review.dto.ReviewRequest
import com.triple.kotprac.review.dto.ToPointRequest
import com.triple.kotprac.review.repository.ReviewRepository
import com.triple.kotprac.user.domain.User
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile


@Service
@Transactional
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val reviewValidator: ReviewValidator,
    private val places: Places,
    private val reviewFileService: ReviewFileService,
    private val restTemplate: RestTemplate
) {
    companion object {
        private const val POINT_EVENT_URL = "http://127.0.0.1:8080/events"
    }

    fun create(request: ReviewRequest.Create, images: List<MultipartFile>?, accessToken: String) {
        val place = places.getOne(request.placeId)
        val review = Review.create(request.content, place, request.userId, reviewValidator)
        val savedReview = reviewRepository.save(review)
        reviewFileService.uploadList(savedReview, images)

        callPointApi(accessToken, savedReview, "ADD", request.userId)
    }

    fun update(reviewId: Long, request: ReviewRequest.Update, images: List<MultipartFile>?, accessToken: String) {
        val review = reviewRepository.findByIdWithImage(reviewId) ?: throw ReviewNotFoundException()
        val fileIdList = reviewFileService.updateFiles(request.fileChange, review, images)
        review.update(request.content, review, reviewValidator, request.userId)

        callPointApiByMod(request, accessToken, review, fileIdList)
    }

    fun delete(reviewId: Long, loginUser: User, accessToken: String) {
        val review = reviewRepository.findByIdWithImage(reviewId) ?: throw ReviewNotFoundException()
        review.delete(review, loginUser, reviewValidator)

        reviewFileService.deleteByReview(review)
        reviewRepository.deleteById(reviewId)
        callPointApi(accessToken, review, "DELETE", loginUser.id!!)
    }

    private fun callPointApi(accessToken: String, review: Review, action: String, loginUserId: Long) {
        val body: RequestEntity<ToPointRequest.Update> = RequestEntity
            .post(POINT_EVENT_URL)
            .header(HttpHeaders.AUTHORIZATION, accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ToPointRequest.Update.addOrDeleteOf(review, action, loginUserId))
        restTemplate.exchange(body, String::class.java)
    }

    private fun callPointApiByMod(
        request: ReviewRequest.Update,
        accessToken: String,
        review: Review,
        fileIdList: List<Long>
    ) {
        val body: RequestEntity<ToPointRequest.Update> = RequestEntity
            .post(POINT_EVENT_URL)
            .header(HttpHeaders.AUTHORIZATION, accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ToPointRequest.Update.modOf(request, review, fileIdList))
        restTemplate.exchange(body, String::class.java)
    }
}