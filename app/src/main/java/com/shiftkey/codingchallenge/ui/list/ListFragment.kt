package com.shiftkey.codingchallenge.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shiftkey.codingchallenge.databinding.FragmentListBinding
import com.shiftkey.codingchallenge.model.ShiftRepository
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var shiftRepository: ShiftRepository

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            Log.d(this::class.java.simpleName, "shifts - ${shiftRepository.fetchAvailableShiftsForWeek().body()}")
        }
    }
}
