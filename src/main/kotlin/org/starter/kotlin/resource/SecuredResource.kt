package org.starter.kotlin.resource

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/api")
class SecuredResource {

	@PostMapping("/dosomething")
	fun log(@RequestBody message: String): String {
		println("Printing $message")
		return "Success"
	}

	@GetMapping("/print")
	fun print(): String {
		val message = "Welcome"
		println("Printing $message")
		return "Success"
	}
}