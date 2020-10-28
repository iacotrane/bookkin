package it.iacotrane.bookkin.mapper

import it.iacotrane.bookkin.model.Field
import it.iacotrane.bookkin.model.dto.FieldDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface FieldMapper {

    fun convertToDto(field: Field): FieldDto

    @InheritInverseConfiguration
    fun convertToEntity(fieldDto: FieldDto): Field

}