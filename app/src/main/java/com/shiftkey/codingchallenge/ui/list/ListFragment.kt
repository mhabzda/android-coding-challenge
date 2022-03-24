package com.shiftkey.codingchallenge.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.shiftkey.codingchallenge.databinding.FragmentListBinding
import com.shiftkey.codingchallenge.ui.list.ListFragmentDirections.Companion.actionToItemFragment
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect.OpenItemScreen
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect.RefreshItems
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect.ShowError
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: ListViewModel

    private lateinit var adapter: ListAdapter

    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ListAdapter { viewModel.onItemClick(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listRecycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.listRecycler.adapter = adapter
        binding.listSwipeRefresh.setOnRefreshListener { viewModel.onRefresh() }

        viewModel.pagingEvents.observeWhenStarted(viewLifecycleOwner, adapter::submitData)
        viewModel.state.observeWhenStarted(viewLifecycleOwner, ::renderState)
        viewModel.sideEffects.observeWhenStarted(viewLifecycleOwner, ::handleSideEffect)

        viewModel.onStart(adapter.loadStateFlow)
    }

    private fun renderState(state: ListViewState) = with(binding) {
        listSwipeRefresh.isRefreshing = state.isRefreshing
    }

    private fun handleSideEffect(sideEffect: ListViewSideEffect) = when (sideEffect) {
        RefreshItems -> adapter.refresh()
        is OpenItemScreen -> findNavController().navigate(actionToItemFragment(sideEffect.shiftParcelable))
        is ShowError -> Toast.makeText(requireActivity(), sideEffect.message, Toast.LENGTH_LONG).show()
    }

    private inline fun <reified T> Flow<T>.observeWhenStarted(
        lifecycleOwner: LifecycleOwner,
        noinline action: suspend (T) -> Unit
    ): Job = lifecycleOwner.lifecycleScope.launch {
        flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED).collectLatest(action)
    }
}
