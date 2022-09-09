package com.triple.kotprac.place.domain

import com.triple.kotprac.common.entity.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "place")
class Place(
    @Column(name = "name", nullable = false)
    private val name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id", nullable = false)
    val id: Long? = null,
) : BaseTimeEntity() {
    companion object {
        fun create(name: String): Place {
            return Place(name = name)
        }
    }
}