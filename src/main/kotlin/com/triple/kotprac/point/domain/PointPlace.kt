package com.triple.kotprac.point.domain

import com.triple.kotprac.common.entity.BaseTimeEntity
import javax.persistence.*


@Entity
@Table(name = "point_place")
class PointPlace(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_place_id")
    val id: Long? = null,
    var firstReviewId: Long,
    @Column(unique = true)
    val placeId: Long,
) : BaseTimeEntity() {
    fun update(firstReviewId: Long) {
        this.firstReviewId = firstReviewId
    }

    companion object {
        fun create(firstReviewId: Long, placeId: Long): PointPlace {
            return PointPlace(firstReviewId = firstReviewId, placeId = placeId)
        }
    }
}