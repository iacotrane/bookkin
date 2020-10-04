package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.Field
import org.springframework.data.jpa.repository.JpaRepository

interface FieldRepository : JpaRepository<Field, Long> {
}