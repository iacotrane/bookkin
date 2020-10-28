package it.iacotrane.bookkin.mapper

import it.iacotrane.bookkin.model.Reservation
import it.iacotrane.bookkin.model.dto.ReservationDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface ReservationMapper {

    @Mappings(
            Mapping(source = "bookingRange.start", target = "reservationStartTime"),
            Mapping(source = "bookingRange.end", target = "reservationEndTime"),
            Mapping(source = "bookingRange.price", target = "price"),
            Mapping(source = "bookingRange.field.name", target = "fieldName")
    )
    fun convertToDto(reservation: Reservation): ReservationDto

}