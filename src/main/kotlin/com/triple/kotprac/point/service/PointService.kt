package com.triple.kotprac.point.service

import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.dto.PointHistoryResponse
import com.triple.kotprac.point.dto.PointRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointService(
    private val reviewAddPointService: ReviewAddPointService,
    private val reviewModPointService: ReviewModPointService,
    private val reviewDeletePointService: ReviewDeletePointService
) {
    @Transactional
    fun update(request: PointRequest.Update): PointHistoryResponse.OnlyId {
        val pointHistoryRequest = request.toEntity()
        return when (request.action) {
            PointHistoryAction.ADD -> reviewAddPointService.update(pointHistoryRequest)
            PointHistoryAction.MOD -> reviewModPointService.update(pointHistoryRequest)
            PointHistoryAction.DELETE -> reviewDeletePointService.update(pointHistoryRequest)
        }.let { pointHistory -> PointHistoryResponse.OnlyId.of(pointHistory) }
    }
}