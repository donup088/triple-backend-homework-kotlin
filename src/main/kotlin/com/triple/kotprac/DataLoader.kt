package com.triple.kotprac

import com.triple.kotprac.user.domain.User
import com.triple.kotprac.user.domain.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
        private val userRepository: UserRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (userRepository.findAll().isEmpty()) {
            val users = mutableListOf<User>()
            for (i in 1..10) {
                users.add(User("tester$i"))
            }
            userRepository.saveAll(users)
        }
    }

}