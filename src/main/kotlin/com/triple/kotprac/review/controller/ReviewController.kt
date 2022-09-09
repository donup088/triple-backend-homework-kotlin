package com.triple.kotprac.review.controller

import com.triple.kotprac.auth.security.CurrentUser
import com.triple.kotprac.auth.security.CustomUserDetails
import com.triple.kotprac.common.dto.ApiResult
import com.triple.kotprac.review.dto.ReviewRequest
import com.triple.kotprac.review.service.ReviewService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/review")
class ReviewController(
    private val reviewService: ReviewService
) {
    @PostMapping
    fun create(
        @RequestPart(value = "info") request: ReviewRequest.Create,
        @RequestPart(value = "image", required = false) images: List<MultipartFile>?,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResult<Unit>> {
        reviewService.create(request, images, getAccessToken(httpServletRequest))
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value()))
    }

    @PutMapping("/{reviewId}")
    fun update(
        @PathVariable reviewId: Long,
        @RequestPart(value = "info") request: ReviewRequest.Update,
        @RequestPart(value = "image", required = false) images: List<MultipartFile>?,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResult<Unit>> {
        reviewService.update(reviewId, request, images, getAccessToken(httpServletRequest))
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value()))
    }

    @DeleteMapping("/{reviewId}")
    fun delete(
        @PathVariable reviewId: Long,
        @CurrentUser customUserDetails: CustomUserDetails,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ApiResult<Unit>> {
        reviewService.delete(reviewId, customUserDetails.user, getAccessToken(httpServletRequest))
        return ResponseEntity.ok().body(ApiResult.build(HttpStatus.OK.value()))
    }

    private fun getAccessToken(httpServletRequest: HttpServletRequest): String {
        return httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
    }
}