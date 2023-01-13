package com.etno.microservice.controller.implementation.mail

import com.etno.microservice.controller.MailControllerInterface
import com.etno.microservice.model.dto.mail.MailDetailsDTO
import com.etno.microservice.service.implementation.mail.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MailController(
    private val mailService: MailService
): MailControllerInterface {
    override fun senMail(mailDetailsDTO: MailDetailsDTO): ResponseEntity<MailDetailsDTO> {
        return ResponseEntity.ok().body(mailService.sendSimpleMail(mailDetailsDTO))
    }

    override fun sendMailWithAttachment(mailDetailsDTO: MailDetailsDTO): ResponseEntity<MailDetailsDTO> {
        return ResponseEntity.ok().body(mailService.sendMailWithAttachment(mailDetailsDTO))
    }
}