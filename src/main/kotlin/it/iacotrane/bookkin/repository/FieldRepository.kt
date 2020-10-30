package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.Field
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface FieldRepository : JpaRepository<Field, Long>, QuerydslPredicateExecutor<Field> {

}