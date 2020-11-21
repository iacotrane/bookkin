package it.iacotrane.bookkin.controller

import com.querydsl.core.types.Predicate
import it.iacotrane.bookkin.model.dto.CompanyDto
import it.iacotrane.bookkin.model.dto.UserDto
import it.iacotrane.bookkin.model.request.CompanyRegistrationRequest
import it.iacotrane.bookkin.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AuthenticationController(
        private val authenticationService: AuthenticationService
) {

    @PostMapping("/api/company/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerCompanyManagerUser(@RequestBody body: CompanyRegistrationRequest) {
        authenticationService.createNewCompany(body)
    }

    @GetMapping("/api/company")
    fun findCompanies(@QuerydslPredicate predicate: Predicate): ResponseEntity<List<CompanyDto>> {
        return ResponseEntity.ok(authenticationService.findCompanies(predicate))
    }

    @PostMapping("/api/user/register/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerPlayerUser(@RequestBody body: UserDto, @PathVariable("companyId") companyId: Long) {
        authenticationService.registerPlayer(body, companyId)
    }

    @GetMapping("/api/login")
    fun getCurrentUser(): ResponseEntity<UserDto> {
        return ResponseEntity.ok(authenticationService.getCurrentUser())
    }

}