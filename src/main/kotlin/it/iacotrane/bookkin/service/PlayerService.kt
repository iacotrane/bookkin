package it.iacotrane.bookkin.service

import com.querydsl.core.types.Predicate
import it.iacotrane.bookkin.exception.ReservationAlreadyExistsException
import it.iacotrane.bookkin.mapper.BookingRangeMapper
import it.iacotrane.bookkin.mapper.FieldMapper
import it.iacotrane.bookkin.mapper.ReservationMapper
import it.iacotrane.bookkin.model.QBookingRange
import it.iacotrane.bookkin.model.QField
import it.iacotrane.bookkin.model.QReservation
import it.iacotrane.bookkin.model.dto.BookingRangeDto
import it.iacotrane.bookkin.model.dto.FieldDto
import it.iacotrane.bookkin.model.dto.ReservationDto
import it.iacotrane.bookkin.model.dto.UserDto
import it.iacotrane.bookkin.repository.BookingRangeRepository
import it.iacotrane.bookkin.repository.FieldRepository
import it.iacotrane.bookkin.repository.ReservationRepository
import it.iacotrane.bookkin.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PlayerService(
        private val fieldRepository: FieldRepository,
        private val fieldMapper: FieldMapper,
        private val bookingRangeRepository: BookingRangeRepository,
        private val bookingRangeMapper: BookingRangeMapper,
        private val reservationService: ReservationService,
        private val reservationMapper: ReservationMapper
) {

    fun findAvailableFields(predicate: Predicate): List<FieldDto> {
        val authentication = SecurityContextHolder.getContext().authentication
        val userDto: UserDto = authentication.details as UserDto
        val filter = QField.field.company.id.eq(userDto.companyId).and(QField.field.playable).and(predicate)
        return fieldRepository.findAll(filter).mapNotNull(fieldMapper::convertToDto)
    }

    fun findAvailableBookingRangeForField(fieldId: Long, bookingDate: LocalDate): List<BookingRangeDto> {

        val bookingRangeOfFieldBooked = reservationService.findReservationForField(fieldId, bookingDate).map { reservation -> reservation.bookingRange }
        val bookingRangeFilter = QBookingRange.bookingRange.field.id.eq(fieldId).and(QBookingRange.bookingRange.playable).and(QBookingRange.bookingRange.notIn(bookingRangeOfFieldBooked))
        return bookingRangeRepository.findAll(bookingRangeFilter).map(bookingRangeMapper::convertToDto)
    }

    fun bookField(fieldId: Long, bookingRangeId: Long, date: LocalDate) {

        reservationService.checkIfReservationAlreadyExists(bookingRangeId, date)

        val authentication = SecurityContextHolder.getContext().authentication
        val userDto: UserDto = authentication.details as UserDto

        reservationService.book(userDto.id, bookingRangeId, date)
    }

    fun findMyReservations(predicate: Predicate): List<ReservationDto> {
        val authentication = SecurityContextHolder.getContext().authentication
        val userDto: UserDto = authentication.details as UserDto
        return reservationService.findUserReservations(userDto.id, predicate)
    }

    fun cancelReservation(reservationId: Long) {
        val authentication = SecurityContextHolder.getContext().authentication
        val userDto: UserDto = authentication.details as UserDto
        reservationService.cancelReservation(userDto.id, reservationId)
    }


}