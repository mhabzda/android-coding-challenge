package com.shiftkey.codingchallenge.model

import com.shiftkey.codingchallenge.model.dto.Shifts
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import retrofit2.Response
import javax.inject.Inject

class ShiftRepository @Inject constructor(
    private val shiftApi: ShiftApi
) {

    suspend fun fetchAvailableShiftsForWeek(): Response<Shifts> {
        // Only the Central timezone is being used.
        // It would be better to use local timezone but API input is not prepared for different timezones
        val currentDate = DateTime(DateTimeZone.forID("US/Central"))
        val currentDay = currentDate.toString(DateTimeFormat.forPattern("YYYY-MM-dd"))
        return shiftApi.getAvailableShiftsForWeek(currentDay)
    }
}
