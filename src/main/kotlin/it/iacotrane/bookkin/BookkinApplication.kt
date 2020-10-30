package it.iacotrane.bookkin

import com.google.firebase.FirebaseApp
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class BookkinApplication: SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<BookkinApplication>(*args)
    FirebaseApp.initializeApp();
}
