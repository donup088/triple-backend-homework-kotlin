package com.triple.kotprac.point.domain

import com.triple.kotprac.common.entity.BaseTimeEntity
import com.triple.kotprac.point.domain.pointpolicy.PointCalPolicy
import javax.persistence.*
import kotlin.jvm.Transient

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
    @Transient
    var pointCalPolicy: PointCalPolicy? = null
) : BaseTimeEntity() {
    val contentExist
        get() = this.contentLen > 0
    val imgExist
        get() = this.imgCount > 0

    fun calPoint(pointCalPolicy: PointCalPolicy): Int {
        this.pointCalPolicy = pointCalPolicy
        return pointCalPolicy.calculate(this)
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