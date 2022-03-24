package com.shiftkey.codingchallenge.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.shiftkey.codingchallenge.databinding.FragmentListBinding
import com.shiftkey.codingchallenge.ui.list.ListFragmentDirections.Companion.actionToItemFragment
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect.OpenItemScreen
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect.RefreshItems
import com.shiftkey.codingchallenge.ui.list.ListViewSideEffect.ShowError
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: ListViewModel

    private lateinit var adapter: ListAdapter

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListAdapter { viewModel.onItemClick(it) }
        binding.listRecycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.listRecycler.adapter = adapter
        binding.listSwipeRefresh.setOnRefreshListener { viewModel.onRefresh() }

        viewModel.pagingEvents.onEach(adapter::submitData).launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.state.onEach(::renderState).launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.sideEffects.onEach(::handleSideEffect).launchIn(viewLifecycleOwner.lifecycleScope)

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
}
