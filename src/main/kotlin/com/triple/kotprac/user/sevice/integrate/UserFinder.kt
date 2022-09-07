package com.triple.kotprac.user.sevice.integrate

import com.triple.kotprac.common.exception.user.UserNotFoundException
import com.triple.kotprac.user.domain.User
import com.triple.kotprac.user.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
internal class UserFinder(
        private val userRepository: UserRepository
): Users {
    override fun getOneByNickname(nickname: String): User {
        return userRepository.findByNickname(nickname)?: throw UserNotFoundException()
    }

}