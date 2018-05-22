package org.starter.kotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import org.starter.kotlin.model.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
	fun findByUsername(username: String ): User
}