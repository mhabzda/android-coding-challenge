package com.shiftkey.codingchallenge.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shiftkey.codingchallenge.model.entity.ShiftEntity
import com.shiftkey.codingchallenge.model.mapper.ShiftsMapper
import com.shiftkey.codingchallenge.model.network.ResponseAdapter
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat

class ShiftsPagingSource(
    private val shiftApi: ShiftApi,
    private val responseAdapter: ResponseAdapter,
    private val shiftsMapper: ShiftsMapper
) : PagingSource<Int, ShiftEntity>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ShiftEntity> {
        val pageNumber = params.key ?: 0

        // Only the Central timezone is being used.
        // It would be better to use local timezone but API input is not prepared for different timezones
        val currentDate = DateTime(DateTimeZone.forID("US/Central")).plusWeeks(pageNumber)
        val currentDay = currentDate.toString(DateTimeFormat.forPattern("YYYY-MM-dd"))
        val result = responseAdapter.map(shiftApi.getAvailableShiftsForWeek(currentDay))

        return if (result.isSuccess) {
            val shifts = result.map { shiftsMapper.map(it) }.getOrThrow()
            LoadResult.Page(
                data = shifts,
                prevKey = null,
                nextKey = if (shifts.isNotEmpty()) pageNumber + 1 else null
            )
        } else {
            LoadResult.Error(result.exceptionOrNull() ?: Exception())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ShiftEntity>): Int = 0
}
