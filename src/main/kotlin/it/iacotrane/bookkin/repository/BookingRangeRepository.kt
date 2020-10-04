package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.BookingRange
import org.springframework.data.jpa.repository.JpaRepository

interface BookingRangeRepository : JpaRepository<BookingRange, Long> {
}