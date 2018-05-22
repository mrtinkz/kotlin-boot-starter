package org.starter.kotlin.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired

import org.starter.kotlin.repository.BookRepository
import org.starter.kotlin.model.Book

@Service
@Transactional
open class BookService {
	
	private val log = LoggerFactory.getLogger(BookService::class.java)
	
	@Autowired
	lateinit var bookRepository: BookRepository
	
	fun seedBooks(): Unit {
		log.info("-----Start sample book seed------")
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
}