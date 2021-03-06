package it.iacotrane.bookkin.model

import javax.persistence.*

@Entity
class Field : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    //TODO: Add fields
    @Enumerated(EnumType.STRING)
    lateinit var fieldType: FieldType

    var name: String = ""

    var maxPlayers: Int = 0

    var playable: Boolean = true

    @OneToMany(mappedBy = "field")
    private val bookingRanges: MutableList<BookingRange> = ArrayList()

    @ManyToOne
    lateinit var company: Company

    fun addBookingRange(bookingRange: BookingRange): Field {
        bookingRange.field = this
        bookingRanges.add(bookingRange)
        return this
    }

}