package it.iacotrane.bookkin.controller

import com.querydsl.core.types.Predicate
import it.iacotrane.bookkin.model.Field
import it.iacotrane.bookkin.model.Reservation
import it.iacotrane.bookkin.model.dto.BookingRangeDto
import it.iacotrane.bookkin.model.dto.FieldDto
import it.iacotrane.bookkin.model.dto.ReservationDto
import it.iacotrane.bookkin.service.PlayerService
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class PlayerApiController(private val playerService: PlayerService) {

    @GetMapping("/api/players/fields")
    fun findAvailableFields(@QuerydslPredicate(root = Field::class) predicate: Predicate): ResponseEntity<List<FieldDto>> {
        return ResponseEntity.ok(playerService.findAvailableFields(predicate))
    }

    @GetMapping("/api/player/fields/{fieldId}/date/{date}")
    fun findAvailableBookingRangeForField(@PathVariable("fieldId") fieldId: Long,
                                          @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): ResponseEntity<List<BookingRangeDto>> {
        return ResponseEntity.ok(playerService.findAvailableBookingRangeForField(fieldId, date))
    }

    @PostMapping("/api/player/fields/{fieldId}/book/{bookingRangeId}/date/{date}")
    @ResponseStatus(HttpStatus.CREATED)
    fun bookField(@PathVariable fieldId: Long,
                  @PathVariable bookingRangeId: Long,
                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate) {
        playerService.bookField(fieldId, bookingRangeId, date)
    }

    @GetMapping("/api/player/reservations")
    fun findMyReservations(@QuerydslPredicate(root = Reservation::class) predicate: Predicate): ResponseEntity<List<ReservationDto>> {
        return ResponseEntity.ok(playerService.findMyReservations(predicate))
    }

    @DeleteMapping("/api/player/reservations/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    fun cancelFieldReservation(@PathVariable reservationId: Long) {
        playerService.cancelReservation(reservationId)
    }

}