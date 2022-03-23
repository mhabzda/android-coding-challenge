package com.shiftkey.codingchallenge.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiftkey.codingchallenge.model.ShiftRepository
import com.shiftkey.codingchallenge.model.entity.ShiftEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val shiftRepository: ShiftRepository
) : ViewModel() {

    private val stateFlow = MutableStateFlow(ListViewState())
    val state = stateFlow.asStateFlow()

    fun onStart() = viewModelScope.launch {
        shiftRepository.fetchAvailableShiftsForWeek()
            .onSuccess { stateFlow.value = stateFlow.value.copy(shifts = it) }
            .onFailure { Log.e(this.javaClass.simpleName, it.message, it) }
    }
}

data class ListViewState(
    val shifts: List<ShiftEntity> = emptyList()
)
