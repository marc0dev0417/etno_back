package com.etno.microservice.configuration.mail

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailConfig(
) {
    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "smtp.gmail.com"
        mailSender.port = 587
        mailSender.username = "marcobenegasdev@gmail.com"
        mailSender.password = "odwxuuzejoctvlzo"

        val props = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"

        return mailSender
    }
}