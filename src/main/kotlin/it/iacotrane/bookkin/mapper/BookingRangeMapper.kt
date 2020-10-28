package it.iacotrane.bookkin.mapper

import it.iacotrane.bookkin.model.BookingRange
import it.iacotrane.bookkin.model.dto.BookingRangeDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BookingRangeMapper {

    fun convertToDto(bookingRange: BookingRange): BookingRangeDto

    fun convertToEntity(bookingRangeDto: BookingRangeDto): BookingRange

}