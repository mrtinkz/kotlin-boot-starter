package org.starter.kotlin.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Book(
		var title: String?,

		var content: String?,

		var author: String?,

		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		var id: Long = -1
) { constructor(): this(title = "", content = "", author = "") }
