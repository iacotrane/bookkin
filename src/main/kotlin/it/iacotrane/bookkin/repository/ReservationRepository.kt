package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, Long> {
}