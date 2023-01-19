package com.etno.microservice.service.implementation.mail

import com.etno.microservice.model.dto.mail.MailDetailsDTO
import com.etno.microservice.model.dto.mail.MailDetailsSuccessDTO
import com.etno.microservice.service.MailServiceInterface
import com.etno.microservice.util.Urls
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream


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
            MailDetailsSuccessDTO("Mail has been sent successfully")
        }catch (_: Exception){
            MailDetailsSuccessDTO("Mail has not been sent successfully")
        }

    }

    override fun sendMailWithAttachment(
        address: String,
        message: String,
        subject: String,
        attachment: MultipartFile
    ): MailDetailsSuccessDTO? {
        val mimeMessage = javaMailSender.createMimeMessage()
        try {
            val mimeMessageHelper = MimeMessageHelper(mimeMessage, true)

            // Setting multipart as true for attachments to
            mimeMessageHelper.setFrom(username)
            mimeMessageHelper.setTo(address)
            mimeMessageHelper.setText(message)
            mimeMessageHelper.setSubject(
                subject
            )

            // Adding the attachment
            /*
            val file = FileSystemResource(
                File(mailDetailsDTO.attachment!!)
            )
             */
            val converterFile = File("${Urls.sourceImagePath}\\incidents\\${attachment.originalFilename}")
            converterFile.createNewFile()

            val fos = FileOutputStream(converterFile)
            fos.write(attachment.bytes)
            fos.close()

            mimeMessageHelper.addAttachment(
                converterFile.name, converterFile
            )

            // Sending the mail
            javaMailSender.send(mimeMessage)

            val file = File("src\\main\\resources\\images\\incidents\\${attachment.name}")

            file.delete()

            return MailDetailsSuccessDTO("Mail has been sent successfully")
        }catch (_: Exception){ return MailDetailsSuccessDTO("Mail has not been sent successfully") }
    }
}