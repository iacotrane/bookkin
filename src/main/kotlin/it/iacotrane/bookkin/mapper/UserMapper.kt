package it.iacotrane.bookkin.mapper

import it.iacotrane.bookkin.model.User
import it.iacotrane.bookkin.model.dto.UserDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mappings(
            Mapping(source = "company.id", target = "companyId")
    )
    fun convertToDto(user: User): UserDto

    fun convertToEntity(userDto: UserDto): User

}