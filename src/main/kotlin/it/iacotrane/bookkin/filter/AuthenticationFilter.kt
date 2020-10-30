package it.iacotrane.bookkin.filter

import com.google.firebase.auth.FirebaseAuth
import it.iacotrane.bookkin.exception.UserNotFoundException
import it.iacotrane.bookkin.mapper.UserMapper
import it.iacotrane.bookkin.model.QUser
import it.iacotrane.bookkin.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter(
        private val userRepository: UserRepository,
        private val userMapper: UserMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwt = parseJwt(request)
            val authentication = validateFirebaseToken(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        } catch (e: Exception) {
            logger.error("Cannot set user authentication: {}", e)
        }

        filterChain.doFilter(request, response)
    }

    private fun validateFirebaseToken(token: String): Authentication {
        val idToken = FirebaseAuth.getInstance().verifyIdToken(token)
        assert(idToken != null)
        val booleanExpression = QUser.user.email.eq(idToken.email)
        val user = userRepository.findOne(booleanExpression).orElseThrow { throw UserNotFoundException("User with email: ${idToken.email} not found") }
        val dto = userMapper.convertToDto(user)
        val authorities = HashSet<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(dto.userType.name))
        return UsernamePasswordAuthenticationToken(dto, idToken, authorities)
    }

    private fun parseJwt(request: HttpServletRequest): String {
        val headerAuth = request.getHeader("Authorization")
        if (StringUtils.hasText(headerAuth)) {
            if (headerAuth.startsWith("Bearer ")) {
                return headerAuth.substring(7)
            } else {
                throw RuntimeException("Unable to parse JWT, Authorization header must start with \"Bearer \"")
            }
        } else {
            throw RuntimeException("Unable to parse JWT, header \"Authorization\" must not be empty")
        }
    }

}