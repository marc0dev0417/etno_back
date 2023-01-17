package com.etno.microservice.service

import com.etno.microservice.model.dto.mail.MailDetailsDTO
import com.etno.microservice.model.dto.mail.MailDetailsSuccessDTO
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface MailServiceInterface {
    fun sendSimpleMail(mailDetailsDTO: MailDetailsDTO): MailDetailsSuccessDTO?
    fun sendMailWithAttachment(address: String, message: String, subject: String, attachment: MultipartFile): MailDetailsSuccessDTO?
}