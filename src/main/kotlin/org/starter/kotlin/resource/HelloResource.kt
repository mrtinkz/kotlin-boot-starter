package org.starter.kotlin.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

import org.starter.kotlin.service.HelloService
import org.starter.kotlin.dto.HelloDto

@RestController
@RequestMapping("/demo")
class HelloResource(val helloService: HelloService) {

	@GetMapping("/hello")
	fun helloKotlin(): String {
		return "hello world"
	}

	@GetMapping("/hello-service")
	fun helloKotlinService(): String {
		return helloService.getHello()
	}

	@GetMapping("/hello-dto")
	fun helloDto(): HelloDto {
		return HelloDto("Hello from the dto")
	}
}