package com.shiftkey.codingchallenge.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiftkey.codingchallenge.model.ShiftRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val shiftRepository: ShiftRepository
) : ViewModel() {

    fun onStart() = viewModelScope.launch {
        shiftRepository.fetchAvailableShiftsForWeek()
            .onSuccess { Log.d(this.javaClass.simpleName, "$it") }
            .onFailure { Log.e(this.javaClass.simpleName, it.message, it) }
    }
}
