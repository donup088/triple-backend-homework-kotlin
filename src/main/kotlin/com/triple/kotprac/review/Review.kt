package com.triple.kotprac.review

import com.triple.kotprac.common.entity.BaseTimeEntity
import com.triple.kotprac.place.domain.Place
import com.triple.kotprac.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "review")
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    val id: Long? = null,
    @Column(columnDefinition = "TEXT")
    var content: String?,
    var userId: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    val place: Place,
    @OneToMany(mappedBy = "review")
    val reviewFileList: MutableList<ReviewFile> = mutableListOf(),
) : BaseTimeEntity() {
    fun update(content: String, originReview: Review, reviewValidator: ReviewValidator, loginUserId: Long) {
        reviewValidator.updateValidate(originReview, loginUserId)
        this.content = content
    }

    fun delete(review: Review, loginUserId: User, reviewValidator: ReviewValidator) {
        reviewValidator.deleteValidate(review, loginUserId)
    }

    companion object {
        fun create(content: String, place: Place, loginUserId: Long, reviewValidator: ReviewValidator): Review {
            reviewValidator.createValidate(place, loginUserId)
            return Review(
                content = content,
                place = place,
                userId = loginUserId
            )
        }
    }
}