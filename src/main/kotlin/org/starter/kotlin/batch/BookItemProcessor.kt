package org.starter.kotlin.batch

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.*
import org.springframework.stereotype.Component

import org.starter.kotlin.model.Book

@Component
open class BookItemProcessor : ItemProcessor<Book, Book> {
	private val log = LoggerFactory.getLogger(BookItemProcessor::class.java)

	override fun process(book: Book): Book {
		var transformedBook = Book(book.title, book.content, book.author)
		log.info(transformedBook.toString())
		return transformedBook
	}
}