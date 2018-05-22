package org.starter.kotlin

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.context.annotation.*
import org.springframework.core.annotation.*

import org.starter.kotlin.repository.BookRepository
import org.starter.kotlin.model.Book

/*
 Configuration class to launch application, note the class should have open keyword
 to avoid runtime errors when application boots. @Configuration annotation forces the config
 class to not be final. At the time this writing by default classes in Kotlin are final.
 
 @Author - Vikram Palakurthi
 @Since - April, 2018
*/

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@Order
@ComponentScan("org.starter.kotlin")
open class KotlinApplication

private val log = LoggerFactory.getLogger(KotlinApplication::class.java)

@Bean
fun init(bookRepository: BookRepository) = CommandLineRunner {
	bookRepository.save(Book("Java Pocket Guide", "Quick Reference", "James G"))
	bookRepository.save(Book("Go Pocket Guide", "Quick Reference", "Simon G"))
	bookRepository.save(Book("Kotlin Pocket Guide", "Quick Reference", "Greg G"))
	bookRepository.save(Book("Python Pocket Guide", "Quick Reference", "Mitchell G"))
	bookRepository.save(Book("Javascript Pocket Guide", "Quick Reference", "Sam G"))

	log.info("-------------------------------")
	bookRepository.findAll().forEach { log.info(it.toString()) }
	log.info("-------------------------------")

	// fetch an individual book by ID
	val book = bookRepository.findById(1L)
	book.ifPresent {
		log.info("Book found with findById(1L):")
		log.info("--------------------------------")
		log.info(it.toString())
		log.info("")
	}
}

fun main(args: Array<String>) {
	SpringApplication.run(KotlinApplication::class.java, *args)
}
