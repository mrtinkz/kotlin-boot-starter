package org.starter.kotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.starter.kotlin.interceptor.LogInterceptor




@Configuration
class InterceptorConfig : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LogInterceptor()).addPathPatterns("/**")
        super.addInterceptors(registry)
    }
}