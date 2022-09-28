package com.triple.kotprac.point.domain

import com.triple.kotprac.common.entity.BaseTimeEntity
import com.triple.kotprac.point.domain.pointpolicy.PointCalPolicy
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
    val contentExist
        get() = this.contentLen > 0
    val imgExist
        get() = this.imgCount > 0

    fun calPoint(pointCalPolicy: PointCalPolicy): PointHistory {
        this.point=pointCalPolicy.calculate(this)
        return this
    }
}