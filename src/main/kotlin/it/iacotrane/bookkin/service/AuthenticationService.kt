package it.iacotrane.bookkin.service

import it.iacotrane.bookkin.exception.CompanyNotFoundException
import it.iacotrane.bookkin.mapper.CompanyMapper
import it.iacotrane.bookkin.mapper.UserMapper
import it.iacotrane.bookkin.model.dto.UserDto
import it.iacotrane.bookkin.model.request.CompanyRegistrationRequest
import it.iacotrane.bookkin.repository.CompanyRepository
import it.iacotrane.bookkin.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
        private val companyRepository: CompanyRepository,
        private val companyMapper: CompanyMapper,
        private val userMapper: UserMapper,
        private val firebaseAuthService: FirebaseAuthService
) {

    fun createNewCompany(companyRegistrationRequest: CompanyRegistrationRequest) {
        val (companyDto, userDto) = companyRegistrationRequest
        val companyEntity = companyMapper.convertToEntity(companyDto)
        val userEntity = userMapper.convertToEntity(userDto)
        companyEntity.addUser(userEntity)
        firebaseAuthService.registerUser(userDto)
        companyRepository.save(companyEntity)
    }

    fun registerPlayer(userDto: UserDto, companyId: Long) {
        val company = companyRepository.findById(companyId).orElseThrow { throw CompanyNotFoundException("Company with id: $companyId not found") }
        val userEntity = userMapper.convertToEntity(userDto)
        company.addUser(userEntity)
        firebaseAuthService.registerUser(userDto)
        companyRepository.save(company)
    }

}