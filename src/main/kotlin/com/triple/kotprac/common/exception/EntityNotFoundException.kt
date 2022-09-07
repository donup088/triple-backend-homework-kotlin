package com.triple.kotprac.common.exception

open class EntityNotFoundException (private val msg: String) : BusinessException(msg)