package it.iacotrane.bookkin.model

import java.time.LocalDate
import javax.persistence.*

@Entity
class Reservation : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

    var state: ReservationState = ReservationState.BOOKED

    lateinit var reservationDate: LocalDate

    @ManyToOne
    lateinit var bookingRange: BookingRange

    @ManyToOne
    lateinit var user: User

}