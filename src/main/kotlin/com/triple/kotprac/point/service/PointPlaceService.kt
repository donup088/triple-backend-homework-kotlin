package com.triple.kotprac.point.service

import com.triple.kotprac.point.domain.PointHistory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointPlaceService(
//    private val pointPlaceRepository: PointPlaceRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {

    @Transactional
    fun createAndReturnIsFirstReview(pointHistory: PointHistory): Boolean {
        val firstReview = redisTemplate.opsForValue().get(pointHistory.placeId.toString())
        return if (firstReview == null) {
            redisTemplate.opsForValue().append(pointHistory.placeId.toString(), pointHistory.reviewId.toString())
            println("성공")
            true
        } else {
            println("겹침")
            false
        }
//        val findPointPlace = pointPlaceRepository.findByPlaceId(pointHistory.placeId)
//        return if (findPointPlace == null) {
//            pointPlaceRepository.save(PointPlace.create(pointHistory.reviewId, pointHistory.placeId))
//            true
//        } else {
//            false
//        }
    }
}