package it.iacotrane.bookkin.mapper

import it.iacotrane.bookkin.model.User
import it.iacotrane.bookkin.model.dto.UserDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {

    fun convertToDto(user: User): UserDto

    @InheritInverseConfiguration
    fun convertToEntity(userDto: UserDto): User

}