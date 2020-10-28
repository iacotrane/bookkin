package it.iacotrane.bookkin.model.dto

import java.time.LocalTime

data class BookingRangeDto (
        var id: Long,
        var start: LocalTime,
        var end: LocalTime,
        var price: Double
)