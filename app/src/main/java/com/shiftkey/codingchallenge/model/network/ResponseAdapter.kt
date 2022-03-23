package com.shiftkey.codingchallenge.model.network

import retrofit2.Response
import javax.inject.Inject

class ResponseAdapter @Inject constructor() {

    fun <T> map(response: Response<T>): Result<T> =
        try {
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Network error: ${response.code()}"))
            }
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
}
