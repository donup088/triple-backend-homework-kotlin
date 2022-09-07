package com.triple.kotprac.user.dto

import com.triple.kotprac.user.domain.User

data class UserPointResponse(val point:Int) {
    companion object{
        fun of(user: User):UserPointResponse{
            return UserPointResponse(point = user.point)
        }
    }

}