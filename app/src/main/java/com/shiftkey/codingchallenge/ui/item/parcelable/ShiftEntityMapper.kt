package com.shiftkey.codingchallenge.ui.item.parcelable

import com.shiftkey.codingchallenge.model.entity.ShiftEntity
import javax.inject.Inject

class ShiftEntityMapper @Inject constructor() {

    fun map(entity: ShiftEntity): ShiftParcelable =
        ShiftParcelable(
            id = entity.id,
            startTime = entity.startTime,
            endTime = entity.endTime,
            kind = entity.kind,
            facilityType = entity.facilityType,
            skill = entity.skill,
            specialty = entity.specialty
        )
}
