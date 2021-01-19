package org.starter.kotlin.listener

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class ApplicationStartupListener : ApplicationListener<ContextRefreshedEvent> {

    private val log = LoggerFactory.getLogger(ApplicationStartupListener::class.java)

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        log.info("Not yet implemented")
    }

}