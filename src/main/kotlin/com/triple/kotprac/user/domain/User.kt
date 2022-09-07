package com.triple.kotprac.user.domain


import com.triple.kotprac.common.entity.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class User(
        @Column(name = "nickname", nullable = false)
        private val nickname: String,
        @Column(name = "point", nullable = false)
        var point: Int = 0,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id", nullable = false)
        val id: Long? = null,
) : BaseTimeEntity() {
    fun updatePoint(point: Int) {
        this.point = point;
    }

    companion object {
        fun create(nickname: String, userValidator: UserValidator): User {
            userValidator.signupValidate(nickname)
            return User(nickname = nickname)
        }

    }
}