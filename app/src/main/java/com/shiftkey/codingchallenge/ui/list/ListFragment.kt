package com.shiftkey.codingchallenge.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shiftkey.codingchallenge.databinding.FragmentListBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: ListViewModel

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onStart()
    }
}
