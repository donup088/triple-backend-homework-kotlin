package com.triple.kotprac.user.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.math.min

data class UserSignupRequest(
        @NotBlank
        @Size(min = 1, max = 20, message = "닉네임은 1~20자 이여야 합니다.")
        val nickname: String
)