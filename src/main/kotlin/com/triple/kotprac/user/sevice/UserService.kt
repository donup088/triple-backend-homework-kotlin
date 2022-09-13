package com.triple.kotprac.user.sevice

import com.triple.kotprac.user.domain.User
import com.triple.kotprac.common.exception.user.UserNotFoundException
import com.triple.kotprac.user.domain.UserValidator
import com.triple.kotprac.user.domain.repository.UserRepository
import com.triple.kotprac.user.dto.UserPointResponse
import com.triple.kotprac.user.dto.UserSignupRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
        private val userRepository: UserRepository,
        private val userValidator: UserValidator
) {
    @Transactional
    fun signup(request: UserSignupRequest) {
        val user = User.create(request.nickname, userValidator)
        userRepository.save(user)
    }

    fun getPoint(userId: Long): UserPointResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw UserNotFoundException()
        return UserPointResponse.of(user)
    }

}