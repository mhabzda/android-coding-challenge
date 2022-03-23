package com.shiftkey.codingchallenge.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShiftsDto(
    @Json(name = "data")
    val data: List<ShiftDayDto>
)
