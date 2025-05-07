package kr.hhplus.be.server.support

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class HttpLoggingFilter : OncePerRequestFilter() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.info("Request URI: ${request.requestURI}")
        logger.info("Request Method: ${request.method}")
        filterChain.doFilter(request, response)
        logger.info("Response Status: ${response.status}")
    }
}