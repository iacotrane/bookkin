package it.iacotrane.bookkin

import it.iacotrane.bookkin.model.UserType
import it.iacotrane.bookkin.model.dto.CompanyDto
import it.iacotrane.bookkin.model.dto.UserDto
import it.iacotrane.bookkin.model.request.CompanyRegistrationRequest
import it.iacotrane.bookkin.service.AuthenticationService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookkinApplicationTests {

    @Autowired
    private lateinit var authenticationService: AuthenticationService

    @Test
    fun contextLoads() {
    }

    @Test
    fun createCompanyManagerUser() {
        val companyDto = CompanyDto(0, "Test company", "test description", "test city", "test address")
        val userDto = UserDto(0, "TEST", "MANAGER", "test.bookkin@mailinator.com", "+393270000000", UserType.MANAGER, 0, "testManager")
        authenticationService.createNewCompany(CompanyRegistrationRequest(companyDto, userDto))
    }

}
