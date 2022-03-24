package com.shiftkey.codingchallenge.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shiftkey.codingchallenge.model.entity.ShiftEntity
import com.shiftkey.codingchallenge.model.mapper.ShiftsMapper
import com.shiftkey.codingchallenge.model.network.ResponseAdapter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShiftRepositoryImpl @Inject constructor(
    private val shiftApi: ShiftApi,
    private val responseAdapter: ResponseAdapter,
    private val shiftsMapper: ShiftsMapper
) : ShiftRepository {

    override fun fetchAvailableShifts(): Flow<PagingData<ShiftEntity>> =
        Pager(PagingConfig(pageSize = 100, enablePlaceholders = false), initialKey = 0) {
            ShiftsPagingSource(shiftApi, responseAdapter, shiftsMapper)
        }.flow
}
