package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {
}