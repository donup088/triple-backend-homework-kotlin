package com.triple.kotprac.common.dto

data class ErrorResponse(
        val status: Int,
        val success: Boolean,
        val error: CustomError
) {
    companion object {
        fun build(status: Int, ex: Exception): ErrorResponse {
            return ErrorResponse(
                    status = status,
                    success = false,
                    error = CustomError(type = ex.javaClass.simpleName, info = ex.message)
            )
        }
    }

    data class CustomError(val type: String, val info: String?)
}