package com.triple.kotprac.review.controller

import com.triple.kotprac.auth.security.CurrentUser
import com.triple.kotprac.auth.security.CustomUserDetails
import com.triple.kotprac.common.dto.ApiResult
import com.triple.kotprac.review.dto.ReviewRequest
import com.triple.kotprac.review.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/review")
class ReviewController(
        private val reviewService: ReviewService
) {
    @PostMapping
    fun create(@RequestPart(value = "info") request: ReviewRequest.Create,
               @RequestPart(value = "image", required = false) images: List<MultipartFile>?,
               @CurrentUser customUserDetails: CustomUserDetails): ResponseEntity<ApiResult<Unit>> {
        reviewService.create(request, images, customUserDetails.user)
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value()))
    }

    @PutMapping("/{reviewId}")
    fun update(@PathVariable reviewId: Long,
               @RequestPart(value = "info") request: ReviewRequest.Update,
               @RequestPart(value = "image", required = false) images: List<MultipartFile>?,
               @CurrentUser customUserDetails: CustomUserDetails): ResponseEntity<ApiResult<Unit>> {
        reviewService.update(reviewId, request, images, customUserDetails.user)
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value()))
    }

    @DeleteMapping("/{reviewId}")
    fun delete(@PathVariable reviewId: Long,
               @CurrentUser customUserDetails: CustomUserDetails): ResponseEntity<ApiResult<Unit>> {
        reviewService.delete(reviewId, customUserDetails.user)
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value()))
    }
}