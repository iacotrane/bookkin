package it.iacotrane.bookkin.controller

import it.iacotrane.bookkin.model.dto.BookingRangeDto
import it.iacotrane.bookkin.model.dto.FieldDto
import it.iacotrane.bookkin.service.ManagerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ManagerApiController(
        private val managerService: ManagerService
) {

    @PostMapping("/api/manager/{companyId}/fields")
    fun addOrEditField(@RequestBody fieldDto: FieldDto, @PathVariable("companyId") companyId: Long): ResponseEntity<FieldDto> {
        return ResponseEntity.ok(managerService.addOrEditField(fieldDto, companyId))
    }

    @PostMapping("/api/manager/{companyId}/fields/{fieldId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addOrEditBookingRangeOfField(@RequestBody bookingRangeDto: BookingRangeDto,
                                  @PathVariable("companyId") companyId: Long,
                                  @PathVariable("fieldId") fieldId: Long) {
        managerService.addOrEditBookingRangeOfField(bookingRangeDto, companyId, fieldId)
    }


}