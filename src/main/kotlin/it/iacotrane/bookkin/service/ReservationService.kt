package it.iacotrane.bookkin.service

import com.querydsl.core.types.Predicate
import it.iacotrane.bookkin.exception.BookingRangeNotFoundException
import it.iacotrane.bookkin.exception.ReservationAlreadyExistsException
import it.iacotrane.bookkin.exception.ReservationNotFoundException
import it.iacotrane.bookkin.exception.UserNotFoundException
import it.iacotrane.bookkin.mapper.ReservationMapper
import it.iacotrane.bookkin.model.QReservation
import it.iacotrane.bookkin.model.Reservation
import it.iacotrane.bookkin.model.ReservationState
import it.iacotrane.bookkin.model.dto.ReservationDto
import it.iacotrane.bookkin.repository.BookingRangeRepository
import it.iacotrane.bookkin.repository.ReservationRepository
import it.iacotrane.bookkin.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReservationService(
        private val userRepository: UserRepository,
        private val bookingRangeRepository: BookingRangeRepository,
        private val reservationRepository: ReservationRepository,
        private val reservationMapper: ReservationMapper
) {

    fun findReservationForField(fieldId: Long, date: LocalDate): MutableList<Reservation> {
        val reservationFilter = QReservation.reservation.reservationDate.eq(date).and(QReservation.reservation.bookingRange.field.id.eq(fieldId))
        return reservationRepository.findAll(reservationFilter).toMutableList()
    }

    fun checkIfReservationAlreadyExists(bookingRangeId: Long, date: LocalDate) {
        val reservationFilter = QReservation.reservation.bookingRange.id.eq(bookingRangeId).and(QReservation.reservation.reservationDate.eq(date))
        reservationRepository.findOne(reservationFilter).ifPresent { throw ReservationAlreadyExistsException("A reservation for this range at date: $date already exists") }
    }

    fun book(userId: Long, bookingRangeId: Long, bookingDate: LocalDate): ReservationDto{
        val user = userRepository.findById(userId).orElseThrow { throw UserNotFoundException("User with id $userId not found") }
        val bookingRange = bookingRangeRepository.findById(bookingRangeId).orElseThrow { throw BookingRangeNotFoundException("Booking range with id $bookingRangeId not found") }
        val reservation = Reservation()
        reservation.bookingRange = bookingRange
        reservation.user = user
        reservation.reservationDate = bookingDate
        reservation.state = ReservationState.BOOKED
        reservationRepository.save(reservation)
        return reservationMapper.convertToDto(reservation)
    }

    fun findUserReservations(userId: Long, predicate: Predicate): List<ReservationDto> {
        val reservationFilter = QReservation.reservation.user.id.eq(userId).and(predicate)
        return reservationRepository.findAll(reservationFilter).map(reservationMapper::convertToDto)
    }

    fun cancelReservation(userId: Long, reservationId: Long) {
        val reservationFilter = QReservation.reservation.user.id.eq(userId).and(QReservation.reservation.id.eq(reservationId))
        val reservation = reservationRepository.findOne(reservationFilter).orElseThrow { throw ReservationNotFoundException("Reservation with id $reservationId for user $userId not found") }
        reservation.state = ReservationState.CANCELLED
        reservationRepository.save(reservation)
    }

}