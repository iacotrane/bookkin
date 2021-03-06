package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository : JpaRepository<Reservation, Long>, QuerydslPredicateExecutor<Reservation> {
}