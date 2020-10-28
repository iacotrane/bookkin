package it.iacotrane.bookkin.model

import java.time.LocalTime
import javax.persistence.*

@Entity
class BookingRange : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

    //TODO: Add fields
    @ManyToOne
    lateinit var field: Field

    lateinit var start: LocalTime

    lateinit var end: LocalTime

    var playable: Boolean = true

    var price: Double = 0.0

    @OneToMany(mappedBy = "bookingRange")
    val reservations: List<Reservation> = ArrayList()

}