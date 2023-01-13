package com.etno.microservice.service.implementation.mail

import com.etno.microservice.model.dto.mail.MailDetailsDTO
import com.etno.microservice.service.MailServiceInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
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
    override fun sendSimpleMail(mailDetailsDTO: MailDetailsDTO): MailDetailsDTO? {
        //Creating a simple mail message ->
        val mailMessage = SimpleMailMessage()

        //Setting up necessary details ->
        mailMessage.from = username
        mailMessage.setTo(mailDetailsDTO.address)
        mailMessage.text = mailDetailsDTO.message!!
        mailMessage.subject = mailDetailsDTO.subject!!

        //Sending the mail ->
        javaMailSender.send(mailMessage)
        return mailDetailsDTO
    }

    override fun sendMailWithAttachment(mailDetailsDTO: MailDetailsDTO): MailDetailsDTO? {
        val mimeMessage = javaMailSender.createMimeMessage()
        // Setting multipart as true for attachments to
        val mimeMessageHelper = MimeMessageHelper(mimeMessage, true)
        mimeMessageHelper.setFrom(username)
        mimeMessageHelper.setTo(mailDetailsDTO.address!!)
        mimeMessageHelper.setText(mailDetailsDTO.message!!)
        mimeMessageHelper.setSubject(
            mailDetailsDTO.subject!!
        )

        // Adding the attachment
        val file = FileSystemResource(
            File("C:\\Users\\marcobenegas\\Pictures\\event_2.jpg")
        )

        mimeMessageHelper.addAttachment(
            file.filename, file
        )

        // Sending the mail
        javaMailSender.send(mimeMessage)
        return mailDetailsDTO
    }
}