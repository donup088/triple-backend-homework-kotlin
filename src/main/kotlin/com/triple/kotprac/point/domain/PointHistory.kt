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
    var contentLen: Int = 0,
    var imgCount: Int = 0,

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
        if (this.contentLen > 0) {
            addPoint += 1
        }
        if (this.imgCount > 0) {
            addPoint += 1
        }
        return addPoint
    }

    fun modPointCal(prePointHistory: PointHistory): Int {
        var modPoint = 0
        if ((prePointHistory.contentLen == 0) && (this.contentLen > 0)) {
            modPoint += 1
        } else if (prePointHistory.contentLen > 0 && this.contentLen == 0) {
            modPoint -= 1
        }
        if (prePointHistory.imgCount == 0 && this.imgCount > 0) {
            modPoint += 1
        } else if (prePointHistory.imgCount > 0 && this.imgCount == 0) {
            modPoint -= 1
        }
        return modPoint
    }

    fun updatePoint(point: Int): PointHistory {
        this.point = point
        return this
    }

    fun update(contentExist: Int, imgExist: Int): PointHistory {
        this.contentLen = contentExist
        this.imgCount = imgExist
        return this
    }

    fun delete() {
        this.contentLen = 0
        this.imgCount = 0
    }
}