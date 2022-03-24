package com.shiftkey.codingchallenge.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.shiftkey.codingchallenge.databinding.FragmentItemBinding

class ItemFragment : Fragment() {

    private val args: ItemFragmentArgs by navArgs()

    private lateinit var binding: FragmentItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewStartTime.setTitle(args.shift.startTime)
            viewEndTime.setTitle(args.shift.endTime)
            viewKind.setTitle(args.shift.kind)
            viewFacilityType.setTitle(args.shift.facilityType)
            viewSkill.setTitle(args.shift.skill)
            viewSpeciality.setTitle(args.shift.specialty)
        }
    }
}
