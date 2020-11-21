package it.iacotrane.bookkin.config

import it.iacotrane.bookkin.filter.AuthenticationFilter
import it.iacotrane.bookkin.model.UserType
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val authenticationFilter: AuthenticationFilter
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/manager/**").hasAnyRole(UserType.MANAGER.name)
                .antMatchers("/api/player/**").hasAnyRole(UserType.MANAGER.name, UserType.PLAYER.name)
                .antMatchers("/api/login").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}