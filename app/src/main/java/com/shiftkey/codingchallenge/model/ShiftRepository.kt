package com.shiftkey.codingchallenge.model

import androidx.paging.PagingData
import com.shiftkey.codingchallenge.model.entity.ShiftEntity
import kotlinx.coroutines.flow.Flow

interface ShiftRepository {

    fun fetchAvailableShifts(): Flow<PagingData<ShiftEntity>>
}
