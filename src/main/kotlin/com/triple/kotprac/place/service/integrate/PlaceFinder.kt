package com.triple.kotprac.place.service.integrate

import com.triple.kotprac.common.exception.place.PlaceNotFoundException
import com.triple.kotprac.place.domain.Place
import com.triple.kotprac.place.domain.repository.PlaceRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class PlaceFinder(
        private val placeRepository: PlaceRepository
) : Places {
    override fun getOne(placeId: Long): Place {
        return placeRepository.findByIdOrNull(placeId) ?: throw PlaceNotFoundException()
    }
}