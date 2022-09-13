package com.triple.kotprac.point.domain

import com.triple.kotprac.common.entity.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "point_history")
class PointHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id")
    val id: Long? = null,
    var point: Int = 0,
    var contentExist: Boolean,
    var imgExist: Boolean,

    @Enumerated(EnumType.STRING)
    val type: PointHistoryType,

    @Enumerated(EnumType.STRING)
    val action: PointHistoryAction,

    val reviewId: Long,
    val placeId: Long,
    val userId: Long,
) : BaseTimeEntity() {
    fun addPointCal(isFirstReview: Boolean): Int {
        var addPoint = 0
        if (isFirstReview) {
            addPoint += 1
        }
        if (this.contentExist) {
            addPoint += 1
        }
        if (this.imgExist) {
            addPoint += 1
        }
        return addPoint
    }

    fun modPointCal(prePointHistory: PointHistory): Int {
        var modPoint = 0
        if (!prePointHistory.contentExist && this.contentExist) {
            modPoint += 1
        } else if (prePointHistory.contentExist && !this.contentExist) {
            modPoint -= 1
        }
        if (!prePointHistory.imgExist && this.imgExist) {
            modPoint += 1
        } else if (prePointHistory.imgExist && !this.imgExist) {
            modPoint -= 1
        }
        return modPoint
    }

    fun updatePoint(point: Int): PointHistory {
        this.point = point
        return this
    }

    fun update(contentExist: Boolean, imgExist: Boolean): PointHistory {
        this.contentExist = contentExist
        this.imgExist = imgExist
        return this
    }

    fun delete() {
        this.contentExist = false
        this.imgExist = false
    }
}