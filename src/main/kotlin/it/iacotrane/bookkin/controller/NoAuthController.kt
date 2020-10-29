package it.iacotrane.bookkin.controller

import it.iacotrane.bookkin.model.dto.UserDto
import it.iacotrane.bookkin.model.request.CompanyRegistrationRequest
import it.iacotrane.bookkin.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class NoAuthController(
        private val authenticationService: AuthenticationService
) {

    @PostMapping("/api/company/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerCompanyManagerUser(@RequestBody body: CompanyRegistrationRequest) {
        authenticationService.createNewCompany(body)
    }

    @PostMapping("/api/user/register/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerPlayerUser(@RequestBody body: UserDto, @PathVariable("companyId") companyId: Long) {
        authenticationService.registerPlayer(body, companyId)
    }

}