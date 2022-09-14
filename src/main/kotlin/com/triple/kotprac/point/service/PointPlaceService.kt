package com.triple.kotprac.point.service

import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointPlace
import com.triple.kotprac.point.domain.repository.PointPlaceRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class PointPlaceService(
    private val pointPlaceRepository: PointPlaceRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createAndReturnIsFirstReview(pointHistory: PointHistory): Boolean {
        return try {
            val findPointPlace = pointPlaceRepository.findByPlaceId(pointHistory.placeId)
            if (findPointPlace == null) {
                pointPlaceRepository.save(PointPlace.create(pointHistory.reviewId, pointHistory.placeId))
                true
            } else {
                false
            }
        } catch (e: DataIntegrityViolationException) {
            throw e
        }
    }
}