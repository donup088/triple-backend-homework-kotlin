package com.triple.kotprac.auth.security

import com.triple.kotprac.common.exception.user.UserNotFoundException
import com.triple.kotprac.user.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
        private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String?): UserDetails {
        val user = userRepository.findByIdOrNull(id?.toLong()) ?: throw UserNotFoundException()
        return CustomUserDetails.create(user)
    }

}