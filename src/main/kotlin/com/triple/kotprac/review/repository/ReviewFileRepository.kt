package com.triple.kotprac.review.repository

import com.triple.kotprac.review.ReviewFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ReviewFileRepository : JpaRepository<ReviewFile, Long> {
    @Modifying(clearAutomatically = true)
    @Query("delete from ReviewFile reviewFile where reviewFile.id in :reviewFileIds")
    fun deleteAllByIdIn(@Param("reviewFileIds") reviewFileIds: MutableList<Long>)
}