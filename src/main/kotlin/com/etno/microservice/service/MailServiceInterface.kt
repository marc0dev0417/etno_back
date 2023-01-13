package com.etno.microservice.service

import com.etno.microservice.model.dto.mail.MailDetailsDTO
import org.springframework.stereotype.Service

@Service
interface MailServiceInterface {
    fun sendSimpleMail(mailDetailsDTO: MailDetailsDTO): MailDetailsDTO?
    fun sendMailWithAttachment(mailDetailsDTO: MailDetailsDTO): MailDetailsDTO?
}