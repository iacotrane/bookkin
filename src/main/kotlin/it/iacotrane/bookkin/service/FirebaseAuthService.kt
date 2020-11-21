package it.iacotrane.bookkin.service

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import it.iacotrane.bookkin.mapper.UserMapper
import it.iacotrane.bookkin.model.dto.UserDto
import org.springframework.stereotype.Service

@Service
class FirebaseAuthService(
        private val userMapper: UserMapper,
        private val firebaseApp: FirebaseApp
) {

    fun registerUser(userDto: UserDto) {
        val firebaseUserRequest = userMapper.convertToFirebaseUserRequest(userDto)
        FirebaseAuth.getInstance(firebaseApp).createUser(firebaseUserRequest)
    }

}