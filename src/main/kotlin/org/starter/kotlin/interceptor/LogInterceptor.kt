package org.starter.kotlin.interceptor

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception
import java.lang.String
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class LogInterceptor : HandlerInterceptor {

    private val log = LoggerFactory.getLogger(LogInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        MDC.put("Method", request.method)

        val format = "%-20s%s"
        log.info(String.format(format, "Method", ": " + request.method))
        log.info(String.format(format, "Path", ": " + request.requestURI))
        log.info(String.format(format, "Query", ": " + request.queryString))
        return super.preHandle(request, response, handler)
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        log.info("Finish process with returning status : " + response.status)
        super.afterCompletion(request, response, handler, ex)
    }
}