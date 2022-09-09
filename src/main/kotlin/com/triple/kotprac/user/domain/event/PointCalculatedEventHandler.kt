package com.triple.kotprac.user.domain.event

import com.triple.kotprac.common.exception.user.UserNotFoundException
import com.triple.kotprac.point.domain.event.PointCalculatedEvent
import com.triple.kotprac.user.domain.repository.UserRepository
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PointCalculatedEventHandler(
    private val userRepository: UserRepository
) {
    @Transactional
    @EventListener
    fun handle(event: PointCalculatedEvent) {
        val user = userRepository.findByIdOrNull(event.userId) ?: throw UserNotFoundException()
        user.updatePoint(user.point + event.point)
    }
}