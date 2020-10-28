package it.iacotrane.bookkin.model

import javax.persistence.*

@Entity
class Company : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

    //TODO: Add fields

    var name: String = ""

    var description: String = ""

    var city: String = ""

    var address: String = ""

    @OneToMany(mappedBy = "company")
    val fields: List<Field> = ArrayList()

    @OneToMany(mappedBy = "company")
    val users: List<User> = ArrayList()

}