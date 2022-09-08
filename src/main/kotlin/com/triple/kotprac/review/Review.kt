package com.triple.kotprac.review

import com.triple.kotprac.common.entity.BaseTimeEntity
import com.triple.kotprac.place.domain.Place
import com.triple.kotprac.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "review")
class Review(
        @Column(name = "content", columnDefinition = "TEXT")
        private var content: String?,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "place_id")
        val place: Place,
        @OneToMany(mappedBy = "review")
        val reviewFileList: MutableList<ReviewFile> = mutableListOf(),
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "review_id")
        val id: Long? = null,
) : BaseTimeEntity() {
    fun update(content: String, originReview: Review, reviewValidator: ReviewValidator, loginUser: User) {
        reviewValidator.updateValidate(originReview, loginUser)
        this.content = content
    }

    fun delete(review: Review, loginUser: User, reviewValidator: ReviewValidator) {
        reviewValidator.deleteValidate(review, loginUser)
    }

    companion object {
        fun create(content: String, place: Place, loginUser: User, reviewValidator: ReviewValidator): Review {
            reviewValidator.createValidate(place, loginUser)
            return Review(
                    content = content,
                    place = place,
                    user = loginUser)
        }
    }
}