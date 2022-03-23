package com.shiftkey.codingchallenge.model

import com.shiftkey.codingchallenge.model.dto.Shifts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShiftApi {

    @GET("available_shifts?address=Dallas, TX&type=week")
    suspend fun getAvailableShiftsForWeek(@Query("start") startDay: String): Response<Shifts>
}
