package org.starter.kotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import org.starter.kotlin.model.Book

@Repository
interface BookRepository : JpaRepository<Book, Long> {
}