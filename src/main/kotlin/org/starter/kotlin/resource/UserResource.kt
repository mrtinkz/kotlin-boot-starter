package org.starter.kotlin.resource

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.context.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*
import javax.validation.Valid

import org.starter.kotlin.repository.UserRepository
import org.starter.kotlin.model.User

@RestController
@RequestMapping("/api")
class UserResource {
	
	@Autowired
	lateinit var userRepository: UserRepository
	
	@Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @PostMapping("/user/sign-up")
    fun signUp(@RequestBody user: User): Unit {
        user.password = bCryptPasswordEncoder.encode(user.password);
        userRepository.save(user);
    }
}