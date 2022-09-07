package com.triple.kotprac.common.exception

open class BusinessException(private val msg: String) : RuntimeException(msg)