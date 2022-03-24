package com.shiftkey.codingchallenge.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.shiftkey.codingchallenge.model.ShiftRepository
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect.RefreshItems
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect.ShowError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    shiftRepository: ShiftRepository
) : ViewModel() {

    val pagingEvents = shiftRepository.fetchAvailableShifts()

    private val stateFlow = MutableStateFlow(ListViewState())
    val state = stateFlow.asStateFlow()

    private val sideEffectChannel = Channel<ListViewSideEffect>(Channel.BUFFERED)
    val sideEffects = sideEffectChannel.receiveAsFlow()

    fun onStart(loadStateFlow: Flow<CombinedLoadStates>) = viewModelScope.launch {
        loadStateFlow.collectLatest(::handleLoadState)
    }

    private suspend fun handleLoadState(state: CombinedLoadStates) {
        stateFlow.value = stateFlow.value
            .copy(isRefreshing = state.refresh is LoadState.Loading || state.append is LoadState.Loading)

        val refreshState = state.refresh
        val appendState = state.append
        if (refreshState is LoadState.Error) sideEffectChannel.send(ShowError(refreshState.error.message.orEmpty()))
        if (appendState is LoadState.Error) sideEffectChannel.send(ShowError(appendState.error.message.orEmpty()))
    }

    fun onRefresh() = viewModelScope.launch {
        sideEffectChannel.send(RefreshItems)
    }
}

data class ListViewState(
    val isRefreshing: Boolean = false
)

sealed class ListViewSideEffect {
    object RefreshItems : ListViewSideEffect()
    data class ShowError(val message: String) : ListViewSideEffect()
}
