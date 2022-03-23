package com.shiftkey.codingchallenge.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShiftDto(
    @Json(name = "shift_id")
    val id: String,

    @Json(name = "normalized_start_date_time")
    val startTime: String,

    @Json(name = "normalized_end_date_time")
    val endTime: String,

    @Json(name = "shift_kind")
    val kind: String,

    @Json(name = "facility_type")
    val facilityType: NameDto,

    @Json(name = "skill")
    val skill: NameDto,

    @Json(name = "localized_specialty")
    val specialty: NameDto
)
