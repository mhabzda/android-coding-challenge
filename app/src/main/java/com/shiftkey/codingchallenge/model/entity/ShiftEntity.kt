package com.shiftkey.codingchallenge.model.entity

data class ShiftEntity(
    val id: String,
    val startTime: String,
    val endTime: String,
    val kind: String,
    val facilityType: String,
    val skill: String,
    val specialty: String
)
