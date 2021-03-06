package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.Field
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface FieldRepository : JpaRepository<Field, Long>, QuerydslPredicateExecutor<Field> {

}