package com.triple.kotprac.user.domain

import com.triple.kotprac.common.exception.user.DuplicatedNickNameException
import com.triple.kotprac.user.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserValidator(
        private val userRepository: UserRepository
) {
    fun signupValidate(nickname: String) {
        nicknameValidate(nickname)
    }

    private fun nicknameValidate(nickname: String) {
        if (userRepository.findByNickname(nickname) != null) {
            throw DuplicatedNickNameException()
        }
    }
}