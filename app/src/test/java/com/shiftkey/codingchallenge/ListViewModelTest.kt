package com.shiftkey.codingchallenge

import app.cash.turbine.test
import com.shiftkey.codingchallenge.ui.item.parcelable.ShiftEntityMapper
import com.shiftkey.codingchallenge.ui.list.ListViewModel
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class ListViewModelTest {

    private val viewModel = ListViewModel(
        shiftRepository = mock(),
        shiftEntityMapper = ShiftEntityMapper()
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `refresh items on refresh`() = runTest {
        viewModel.onRefresh()

        viewModel.sideEffects.test {
            assertEquals(ListViewSideEffect.RefreshItems, awaitItem())
        }
    }

    // Other tests for loading and item click can also be implemented here.
    // Additionally, we can also test mappers and maybe even some parts of repository if more things are extracted.
    // Of course, in different files.
}
