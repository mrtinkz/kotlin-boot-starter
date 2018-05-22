package org.starter.kotlin.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
		var username: String,
		var password: String,
		
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		var id: Long = -1
) { constructor(): this(username = "", password = "") }