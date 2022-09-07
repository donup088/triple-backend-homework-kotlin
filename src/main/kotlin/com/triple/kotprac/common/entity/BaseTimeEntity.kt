package com.triple.kotprac.common.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity(
        @CreatedDate
        @Column(name = "created_at", updatable = false)
        val createdAt: LocalDateTime = LocalDateTime.now(),

        @LastModifiedDate
        @Column(name = "updated_at", updatable = false)
        val updatedAt: LocalDateTime = LocalDateTime.now()
)