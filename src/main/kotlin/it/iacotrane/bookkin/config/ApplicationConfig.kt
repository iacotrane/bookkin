package it.iacotrane.bookkin.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.querydsl.core.annotations.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    fun firebaseApp(): FirebaseApp {
        val firebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setProjectId("iacotrane-bookkin-firebase")
                .build()
        return FirebaseApp.initializeApp(firebaseOptions);
    }

}