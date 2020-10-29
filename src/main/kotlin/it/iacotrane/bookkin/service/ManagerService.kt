package it.iacotrane.bookkin.service

import it.iacotrane.bookkin.exception.BookingRangeNotFoundException
import it.iacotrane.bookkin.exception.CompanyNotFoundException
import it.iacotrane.bookkin.exception.FieldNotFoundException
import it.iacotrane.bookkin.mapper.BookingRangeMapper
import it.iacotrane.bookkin.mapper.FieldMapper
import it.iacotrane.bookkin.model.dto.BookingRangeDto
import it.iacotrane.bookkin.model.dto.FieldDto
import it.iacotrane.bookkin.repository.BookingRangeRepository
import it.iacotrane.bookkin.repository.CompanyRepository
import it.iacotrane.bookkin.repository.FieldRepository
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Service
class ManagerService(
        private val companyRepository: CompanyRepository,
        private val fieldRepository: FieldRepository,
        private val fieldMapper: FieldMapper,
        private val bookingRangeRepository: BookingRangeRepository,
        private val bookingRangeMapper: BookingRangeMapper
) {

    fun addOrEditField(fieldDto: FieldDto, companyId: Long): FieldDto {
        val company = companyRepository.findById(companyId).orElseThrow { throw CompanyNotFoundException("Company with id: $companyId not found") }
        val newField = fieldMapper.convertToEntity(fieldDto)
        return if (fieldDto.id != 0L) {
            var olderField = fieldRepository.findById(fieldDto.id).orElseThrow { throw FieldNotFoundException("Field with id: ${fieldDto.id} not found. If you're trying to add a new field use id = 0") }
            BeanUtils.copyProperties(newField, olderField)
            olderField = fieldRepository.save(olderField)
            fieldMapper.convertToDto(olderField)
        } else {
            company.addField(newField)
            companyRepository.save(company);
            fieldMapper.convertToDto(newField)
        }
    }

    fun addOrEditBookingRangeOfField(bookingRangeDto: BookingRangeDto, companyId: Long, fieldId: Long) {
        val company = companyRepository.findById(companyId).orElseThrow { throw CompanyNotFoundException("Company with id: $companyId not found") }
        val fieldEntity = fieldRepository.findById(fieldId).orElseThrow { throw FieldNotFoundException("Field with id: $fieldId not found") }
        val newBookingRange = bookingRangeMapper.convertToEntity(bookingRangeDto)
        if (bookingRangeDto.id != 0L) {
            val olderBookingRange = bookingRangeRepository.findById(bookingRangeDto.id).orElseThrow { throw BookingRangeNotFoundException("Booking range with id: ${bookingRangeDto.id} not found. If you're trying to add a new booking range use id = 0") }
            BeanUtils.copyProperties(newBookingRange, olderBookingRange)
            bookingRangeRepository.save(olderBookingRange)
        } else {
            fieldEntity.addBookingRange(newBookingRange)
            fieldRepository.save(fieldEntity)
        }
    }

}