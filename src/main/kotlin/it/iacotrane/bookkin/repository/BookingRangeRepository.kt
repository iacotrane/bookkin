package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.BookingRange
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface BookingRangeRepository : JpaRepository<BookingRange, Long>, QuerydslPredicateExecutor<BookingRange> {
}