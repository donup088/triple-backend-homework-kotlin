package com.triple.kotprac.review.repository

import com.triple.kotprac.review.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ReviewRepository : JpaRepository<Review, Long> {
    fun findByPlaceIdAndUserId(placeId: Long, userId: Long): Review?

    @Query("select review from Review review left join fetch review.reviewFileList where review.id=:reviewId")
    fun findByIdWithImage(@Param("reviewId") reviewId: Long): Review?
}