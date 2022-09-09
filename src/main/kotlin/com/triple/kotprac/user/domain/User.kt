package com.triple.kotprac.user.domain


import com.triple.kotprac.common.entity.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    val id: Long? = null,
    var nickname: String,
    var point: Int = 0,
) : BaseTimeEntity() {
    fun updatePoint(point: Int) {
        this.point = point
    }

    companion object {
        fun create(nickname: String, userValidator: UserValidator): User {
            userValidator.signupValidate(nickname)
            return User(nickname = nickname)
        }
    }
}