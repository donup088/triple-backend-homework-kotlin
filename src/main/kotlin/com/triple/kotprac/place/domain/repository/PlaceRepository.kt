package com.triple.kotprac.place.domain.repository

import com.triple.kotprac.place.domain.Place
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<Place, Long> {
}