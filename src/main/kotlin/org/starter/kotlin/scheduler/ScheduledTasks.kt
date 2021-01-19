package org.starter.kotlin.scheduler

import java.text.SimpleDateFormat
import java.util.Date

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
open class ScheduledTasks {
	
	private val log = LoggerFactory.getLogger(ScheduledTasks::class.java)

    private val dateFormat: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")

    @Scheduled(fixedRate = 30000)
    fun reportCurrentTime(): Unit {
        log.info("The time is now {}", dateFormat.format(Date()))
    }
}