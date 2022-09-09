package com.triple.kotprac.point.domain.event

import com.triple.kotprac.common.event.Event

class PointCalculatedEvent(
    val userId: Long,
    val point: Int
) : Event()