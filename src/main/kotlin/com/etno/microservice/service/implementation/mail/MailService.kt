package com.etno.microservice.service.implementation.mail

import com.etno.microservice.model.dto.mail.MailDetailsDTO
import com.etno.microservice.model.dto.mail.MailDetailsSuccessDTO
import com.etno.microservice.service.MailServiceInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.io.File


@Service
class MailService(
    @Autowired
    private val javaMailSender: JavaMailSender,
    @Value("spring.mail.username") private val username: String
): MailServiceInterface{
    override fun sendSimpleMail(mailDetailsDTO: MailDetailsDTO): MailDetailsSuccessDTO? {
        return try{
            //Creating a simple mail message ->
            val mailMessage = SimpleMailMessage()

            //Setting up necessary details ->
            mailMessage.from = username
            mailMessage.setTo(mailDetailsDTO.address)
            mailMessage.text = mailDetailsDTO.message!!
            mailMessage.subject = mailDetailsDTO.subject!!

            //Sending the mail ->
            javaMailSender.send(mailMessage)
            MailDetailsSuccessDTO("El correo se ha enviado exitosamente")
        }catch (_: Exception){
            MailDetailsSuccessDTO("El correo no se ha podido enviar")
        }
    }

    override fun sendMailWithAttachment(
        address: String,
        message: String,
        subject: String,
        attachment: String
    ): MailDetailsSuccessDTO? {
        val mimeMessage = javaMailSender.createMimeMessage()
        try {
            val mimeMessageHelper = MimeMessageHelper(mimeMessage, true)

            mimeMessageHelper.setFrom(username)
            mimeMessageHelper.setTo(address)
            mimeMessageHelper.setText(message)
            mimeMessageHelper.setSubject(
                subject
            )

            val pathCut = attachment.substringAfter("8080")
            val newPath = pathCut.replace("/", "//")
            val attachmentModifiedPath = "src//main//resources$newPath"

            val file = FileSystemResource(
                File(attachmentModifiedPath)
            )

            mimeMessageHelper.addAttachment(
                file.filename, file
            )

            javaMailSender.send(mimeMessage)

            return MailDetailsSuccessDTO("El correo se ha enviado exitosamente")
        }catch (e: MailException){ return MailDetailsSuccessDTO("El correo no se ha podido enviar") }
    }
}