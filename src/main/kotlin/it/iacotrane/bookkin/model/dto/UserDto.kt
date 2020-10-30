package it.iacotrane.bookkin.model.dto

import it.iacotrane.bookkin.model.UserType

data class UserDto(
        var id: Long,
        var name: String,
        var surname: String,
        var email: String,
        var phoneNumber: String,
        var userType: UserType,
        var companyId: Long,
        var password: String
) {
    constructor(): this(0, "", "", "", "", UserType.PLAYER, 0, "")
}
