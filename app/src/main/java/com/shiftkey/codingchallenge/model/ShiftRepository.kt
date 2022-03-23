package com.shiftkey.codingchallenge.model

import com.shiftkey.codingchallenge.model.entity.ShiftEntity

interface ShiftRepository {

    suspend fun fetchAvailableShiftsForWeek(): Result<List<ShiftEntity>>
}
