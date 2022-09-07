package com.triple.kotprac.place.service.integrate

import com.triple.kotprac.place.domain.Place

interface Places {
    fun getOne(placeId:Long):Place
}