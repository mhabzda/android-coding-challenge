package com.shiftkey.codingchallenge.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.shiftkey.codingchallenge.databinding.FragmentListBinding
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

        adapter = ListAdapter()
        binding.listRecycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.listRecycler.adapter = adapter

        viewModel.state.onEach(::renderState).launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.onStart()
    }

    private fun renderState(state: ListViewState) {
        adapter.list = state.shifts
    }
}
