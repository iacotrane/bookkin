package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}