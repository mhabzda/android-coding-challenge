package com.shiftkey.codingchallenge.model

import com.shiftkey.codingchallenge.model.entity.ShiftEntity
import com.shiftkey.codingchallenge.model.network.ResponseAdapter
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class ShiftRepositoryImpl @Inject constructor(
    private val shiftApi: ShiftApi,
    private val responseAdapter: ResponseAdapter,
    private val shiftsMapper: ShiftsMapper
) : ShiftRepository {

    override suspend fun fetchAvailableShiftsForWeek(): Result<List<ShiftEntity>> {
        // Only the Central timezone is being used.
        // It would be better to use local timezone but API input is not prepared for different timezones
        val currentDate = DateTime(DateTimeZone.forID("US/Central"))
        val currentDay = currentDate.toString(DateTimeFormat.forPattern("YYYY-MM-dd"))
        val result = responseAdapter.map(shiftApi.getAvailableShiftsForWeek(currentDay))

        return result.map { shiftsMapper.map(it) }
    }
}
