package com.triple.kotprac.user.domain.repository

import com.triple.kotprac.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByNickname(nickname: String): User?
}