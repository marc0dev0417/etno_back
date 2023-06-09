package com.etno.microservice.configuration.security

import com.etno.microservice.security.JwtRequestFilter
import com.etno.microservice.service.implementation.jwt.JwtUserDetailsService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig() {
    @Autowired
    lateinit var jwtRequestFilter: JwtRequestFilter

    @Autowired
    lateinit var jwtUserDetailsService: JwtUserDetailsService

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder){
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(BCryptPasswordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? {
        return authenticationConfiguration.authenticationManager
    }

    @Throws(Exception::class)
    @Bean
    fun configure(http: HttpSecurity?): SecurityFilterChain {
        http?.csrf()?.disable()?.authorizeRequests()?.antMatchers("/register", "/login", "/users/villagers", "/users/**", "/users/add/incident", "/users/add/event/subscription", "/users/dropout/event/subscription", "/send-notification",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/bandos/**",
            "/links/**",
            "/reserves/**",
            "/places/**",
            "/halls/**",
            "/ads/**",
            "/sponsors/**",
            "/sendMail/**",
            "/FCMTokens/**",
            "/events/**",
            "/quizzes/**",
            "/quiz_results/**",
            "/incidents/**",
            "/images/**",
            "/festivities/**",
            "/tourism/**",
            "/pharmacies/**",
            "/custom_links/**",
            "/reserveUsers/**",
            "/deaths/**",
            "/services/**",
            "/news/**",
            "/subscription_users")
            ?.permitAll()?.anyRequest()?.authenticated()?.and()?.exceptionHandling()
            ?.authenticationEntryPoint{ _: HttpServletRequest?, response: HttpServletResponse, _: AuthenticationException? ->
                val responseMap: MutableMap<String, Any> = HashMap()
                val mapper = ObjectMapper()
                response.status = 401
                responseMap["error"] = true
                responseMap["message"] = "Unauthorized"
                response.setHeader("content-type", "application/json")
                val responseMsg = mapper.writeValueAsString(responseMap)
                response.writer.write(responseMsg)
            }?.and()?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ?.and()?.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http!!.build()
    }
}