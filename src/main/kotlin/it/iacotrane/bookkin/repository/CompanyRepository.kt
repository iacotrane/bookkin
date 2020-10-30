package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface CompanyRepository : JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {
}