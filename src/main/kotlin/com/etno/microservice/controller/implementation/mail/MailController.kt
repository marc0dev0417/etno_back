package com.etno.microservice.controller.implementation.mail

import com.etno.microservice.controller.MailControllerInterface
import com.etno.microservice.model.dto.mail.MailDetailsDTO
import com.etno.microservice.model.dto.mail.MailDetailsSuccessDTO
import com.etno.microservice.service.implementation.mail.MailService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MailController(
    private val mailService: MailService
): MailControllerInterface {
    override fun senMail(mailDetailsDTO: MailDetailsDTO): ResponseEntity<MailDetailsSuccessDTO> {
        return ResponseEntity.ok().body(mailService.sendSimpleMail(mailDetailsDTO))
    }

    override fun sendMailWithAttachment(
        @RequestParam(name = "address", required = true) address: String,
        @RequestParam(name = "message", required = true) message: String,
        @RequestParam(name = "subject", required = true) subject: String,
        @RequestParam(name = "attachment", required = true) attachment: String
    ): ResponseEntity<MailDetailsSuccessDTO> {
        return ResponseEntity.ok().body(mailService.sendMailWithAttachment(address, message, subject, attachment))
    }
}