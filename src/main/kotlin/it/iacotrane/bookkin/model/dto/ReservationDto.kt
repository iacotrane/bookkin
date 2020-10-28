package it.iacotrane.bookkin.model.dto

import it.iacotrane.bookkin.model.ReservationState
import java.time.LocalDate
import java.time.LocalTime

data class ReservationDto(
        var id: Long,
        var state: ReservationState,
        var reservationDate: LocalDate?,
        var reservationStartTime: LocalTime?,
        var reservationEndTime: LocalTime?,
        var price: Double,
        var fieldName: String
) {
    constructor(): this(0, ReservationState.BOOKED, null, null, null, 0.0, "")
}