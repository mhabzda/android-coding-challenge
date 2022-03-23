package com.shiftkey.codingchallenge.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShiftDayDto(
    @Json(name = "date")
    val date: String,

    @Json(name = "shifts")
    val shifts: List<ShiftDto>
)
