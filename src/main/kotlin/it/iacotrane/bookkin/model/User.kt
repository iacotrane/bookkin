package it.iacotrane.bookkin.model

import javax.persistence.*

@Entity
class User : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

    var name: String = ""

    var surname: String = ""

    @Column(unique = true)
    var email: String = ""

    var phoneNumber: String = ""

    @Enumerated(EnumType.STRING)
    lateinit var userType: UserType

    @ManyToOne
    lateinit var company: Company

    @OneToMany(mappedBy = "user")
    val reservations: List<Reservation> = ArrayList()

    //Todo: Add fields, do not add username and pwd (try to use Firebase auth)

}