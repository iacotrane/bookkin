package it.iacotrane.bookkin.repository

import it.iacotrane.bookkin.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface UserRepository : JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
}