package it.iacotrane.bookkin.model.dto

import it.iacotrane.bookkin.model.UserType

data class UserDto(
        var id: Long,
        var name: String,
        var surname: String,
        var email: String,
        var userType: UserType
)
