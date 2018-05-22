package org.starter.kotlin.resource

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.context.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import javax.validation.Valid

import org.starter.kotlin.repository.BookRepository
import org.starter.kotlin.service.BookService
import org.starter.kotlin.model.Book

@RestController
@RequestMapping("/api")
class BookResource {

	@Autowired
	lateinit var bookRepository: BookRepository
	
	@Autowired
	lateinit var bookService: BookService

	@GetMapping("/seedBooks")
	fun seedSampleBooks(): Unit = bookService.seedBooks()
	
	@GetMapping("/books")
	fun getAllBooks(): List<Book> = bookRepository.findAll()

	@PostMapping("/books")
	fun createNewBook(@Valid @RequestBody book: Book): Book = bookRepository.save(book)

	@GetMapping("books/{id}")
	fun getBookById(@PathVariable(value = "id") bookId: Long): ResponseEntity<Book> {
		return bookRepository.findById(bookId).map { book ->
			ResponseEntity.ok(book)
		}.orElse(ResponseEntity.notFound().build())
	}

	@PutMapping("books/{id}")
	fun updateBookById(@PathVariable(value = "id") bookId: Long,
					   @Valid @RequestBody book: Book): ResponseEntity<Book> {
		return bookRepository.findById(bookId).map { existingBook ->
			val updatedBook: Book = existingBook.copy(title = book.title, content = book.content)
			ResponseEntity.ok().body(bookRepository.save(updatedBook))
		}.orElse(ResponseEntity.notFound().build())
	}

	@DeleteMapping("/books/{id}")
	fun deleteBookById(@PathVariable(value = "id") bookId: Long): ResponseEntity<Void> {
		return bookRepository.findById(bookId).map { book ->
			bookRepository.delete(book)
			ResponseEntity<Void>(HttpStatus.OK)
		}.orElse(ResponseEntity.notFound().build())
	}
}