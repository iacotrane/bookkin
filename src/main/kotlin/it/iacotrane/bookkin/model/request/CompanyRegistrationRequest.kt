package it.iacotrane.bookkin.model.request

import it.iacotrane.bookkin.model.dto.CompanyDto
import it.iacotrane.bookkin.model.dto.UserDto

data class CompanyRegistrationRequest(var companyDto: CompanyDto, var userDto: UserDto)