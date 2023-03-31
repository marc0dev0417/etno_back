package com.etno.microservice.service

import com.etno.microservice.model.dto.mail.MailDetailsDTO
import com.etno.microservice.model.dto.mail.MailDetailsSuccessDTO
import org.springframework.stereotype.Service

@Service
interface MailServiceInterface {
    fun sendSimpleMail(mailDetailsDTO: MailDetailsDTO): MailDetailsSuccessDTO?
    fun sendMailWithAttachment(address: String, message: String, subject: String, attachment: String): MailDetailsSuccessDTO?
}