package com.triple.kotprac.common.exception.place

import com.triple.kotprac.common.exception.EntityNotFoundException

class PlaceNotFoundException: EntityNotFoundException("없는 장소입니다.") {
}