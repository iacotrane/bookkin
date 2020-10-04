package it.iacotrane.bookkin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookkinApplication

fun main(args: Array<String>) {
    runApplication<BookkinApplication>(*args)
}
