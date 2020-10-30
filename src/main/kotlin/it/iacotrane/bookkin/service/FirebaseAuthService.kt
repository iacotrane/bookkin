package it.iacotrane.bookkin.service

import com.google.firebase.auth.FirebaseAuth
import it.iacotrane.bookkin.mapper.UserMapper
import it.iacotrane.bookkin.model.dto.UserDto
import org.springframework.stereotype.Service

@Service
class FirebaseAuthService(
        private val userMapper: UserMapper
) {

    fun registerUser(userDto: UserDto) {
        val firebaseUserRequest = userMapper.convertToFirebaseUserRequest(userDto)
        FirebaseAuth.getInstance().createUser(firebaseUserRequest)
    }

}