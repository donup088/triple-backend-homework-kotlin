package com.triple.kotprac.point.domain.repository

import com.triple.kotprac.point.domain.PointHistory
import org.springframework.data.jpa.repository.JpaRepository

interface PointHistoryRepository : JpaRepository<PointHistory, Long>, PointHistoryRepositoryCustom