package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.BookingRange
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface BookingRangeRepository : JpaRepository<BookingRange, Long>, QuerydslPredicateExecutor<BookingRange> {
}