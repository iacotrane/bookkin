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

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL])
    private val fields: MutableList<Field> = ArrayList()

    @OneToMany(mappedBy = "company", cascade = [CascadeType.PERSIST])
    private val users: MutableList<User> = ArrayList()

    fun addUser(user: User): Company {
        user.company = this
        users.add(user)
        return this
    }

    fun addField(field: Field): Company {
        field.company = this
        fields.add(field)
        return this
    }

}