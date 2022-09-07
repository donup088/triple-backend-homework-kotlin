package com.triple.kotprac.user.sevice.integrate

import com.triple.kotprac.user.domain.User

interface Users {
    fun getOneByNickname(nickname: String): User
}