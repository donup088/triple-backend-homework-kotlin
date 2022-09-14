package com.triple.kotprac.point.domain.repository

import com.triple.kotprac.point.domain.PointPlace
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PointPlaceRepository : JpaRepository<PointPlace, Long> {
    @Query("select pointPlace from PointPlace pointPlace where pointPlace.id=:placeId")
    fun findByPlaceId(placeId: Long): PointPlace?
}