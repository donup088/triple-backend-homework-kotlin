package com.triple.kotprac.common.dto

class ApiResult<T>(
        val status: Int,
        val success: Boolean,
        val data: T? = null
) {
    companion object {
        fun <T> build(status: Int, data: T): ApiResult<T> {
            return ApiResult(
                    status = status,
                    data = data,
                    success = true)
        }

        fun <T> build(status: Int): ApiResult<T> {
            return ApiResult(
                    status = status,
                    success = true)
        }
    }
}