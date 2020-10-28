package it.iacotrane.bookkin.mapper

import it.iacotrane.bookkin.model.Company
import it.iacotrane.bookkin.model.dto.CompanyDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.springframework.stereotype.Service

@Mapper(componentModel = "spring")
interface CompanyMapper {

    fun convertToDto(company: Company): CompanyDto

    @InheritInverseConfiguration
    fun convertToEntity(companyDto: CompanyDto): Company

}