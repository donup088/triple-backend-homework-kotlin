package com.triple.kotprac.common.event

abstract class Event(
    val timestamp: Long = System.currentTimeMillis()
)