package com.etno.microservice.security

import com.etno.microservice.service.implementation.jwt.JwtUserDetailsService
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import java.lang.IllegalArgumentException

@Component
class JwtRequestFilter(
    val jwtTokenUtil: JwtTokenUtil,
    val jwtDetailsService: JwtUserDetailsService
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestTokenHeader = request.getHeader("Authorization")
            if (org.apache.commons.lang3.StringUtils.startsWith(requestTokenHeader, "Bearer ")){
                val jwtToken = requestTokenHeader.substring(7)
                try {
                    val username: String = jwtTokenUtil.getUsernameFromToken(jwtToken) ?: ""
                    if(org.apache.commons.lang3.StringUtils.isNotEmpty(username) && null == SecurityContextHolder.getContext().authentication){
                        val userDetails: UserDetails = jwtDetailsService.loadUserByUsername(username)
                        if(jwtTokenUtil.validateToken(jwtToken, userDetails) == true){
                            val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                            usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                        }
                    }
                }catch (error: IllegalArgumentException){
                    logger.error("Unable to fetch JWT Token")
                }catch (error: ExpiredJwtException){
                    logger.error("JWT Token is expired")
                }catch (error: Exception){
                    logger.error(error.message)
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String")
            }
        filterChain.doFilter(request, response)
    }
}