package com.triple.kotprac.place.domain

import com.triple.kotprac.common.entity.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "place")
class Place(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id", nullable = false)
    val id: Long? = null,
    val name: String,
) : BaseTimeEntity() {
    companion object {
        fun create(name: String): Place {
            return Place(name = name)
        }
    }
}