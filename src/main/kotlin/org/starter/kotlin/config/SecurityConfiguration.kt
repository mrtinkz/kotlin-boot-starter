package org.starter.kotlin.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpMethod
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
@EnableWebSecurity
open class SecurityConfiguration : WebSecurityConfigurerAdapter() {

	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http
				.authorizeRequests()
				.antMatchers("/", "/home", "/demo/**", "/api/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.permitAll()
	}

	@Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder  {
        return BCryptPasswordEncoder();
    }
	
	@Bean
	override fun userDetailsService(): UserDetailsService {
		val user: UserDetails =
				User.withDefaultPasswordEncoder()
						.username("user")
						.password("password")
						.roles("USER")
						.build();
		return InMemoryUserDetailsManager(user);
	}
}