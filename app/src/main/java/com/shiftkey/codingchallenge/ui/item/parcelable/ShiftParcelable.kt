package com.shiftkey.codingchallenge.ui.item.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShiftParcelable(
    val id: String,
    val startTime: String,
    val endTime: String,
    val kind: String,
    val facilityType: String,
    val skill: String,
    val specialty: String
) : Parcelable
