package com.triple.kotprac.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import com.querydsl.jpa.impl.JPAQueryFactory

@Configuration
@EnableJpaAuditing
class JpaConfig(
        @PersistenceContext
        private val entityManager: EntityManager
) {
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}