package com.shiftkey.codingchallenge.model

import com.shiftkey.codingchallenge.model.dto.ShiftsDto
import com.shiftkey.codingchallenge.model.entity.ShiftEntity
import javax.inject.Inject

class ShiftsMapper @Inject constructor() {

    fun map(shifts: ShiftsDto): List<ShiftEntity> =
        shifts.data
            .filter { day -> day.shifts.isNotEmpty() }
            .flatMap { day -> day.shifts }
            .map {
                ShiftEntity(
                    id = it.id,
                    startTime = it.startTime,
                    endTime = it.endTime,
                    kind = it.kind
                )
            }
}
