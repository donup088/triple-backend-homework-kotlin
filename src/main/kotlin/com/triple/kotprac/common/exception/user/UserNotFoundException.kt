package com.triple.kotprac.common.exception.user

import com.triple.kotprac.common.exception.EntityNotFoundException

class UserNotFoundException : EntityNotFoundException("없는 유저입니다.") {
}