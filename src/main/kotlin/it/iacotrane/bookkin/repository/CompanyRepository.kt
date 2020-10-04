package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, Long> {
}