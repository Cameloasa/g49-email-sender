package se.lexicon.g49emailsender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import se.lexicon.g49emailsender.config.EmailProperties;
import se.lexicon.g49emailsender.domain.dto.EmailDTO;
import se.lexicon.g49emailsender.domain.entity.Email;
import se.lexicon.g49emailsender.exception.EmailException;
import se.lexicon.g49emailsender.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmailProperties emailProperties;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository, EmailProperties emailProperties, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.emailProperties = emailProperties;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailDTO dto) {
        //Validate the params
        if(dto == null) throw new IllegalArgumentException("DTO cannot be null");


try {
    //1. Create a mime message
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    //2. create a mime message helper
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
    //mime message set
    mimeMessage.setContent(dto.getHtml(), "text/html");
    helper.setTo(dto.getTo());
    helper.setFrom(emailProperties.getUsername());
    helper.setSubject(dto.getSubject());


    //convert DTO to Entity using builder
    Email emailEntity = Email.builder()
            .to(dto.getTo())
            .from(emailProperties.getUsername())
            .subject(dto.getSubject())
            .content(dto.getHtml())
            .type(dto.getType())
            .build();

    emailRepository.save(emailEntity);

    // send the email

    javaMailSender.send(mimeMessage);

}catch (MessagingException e) {
    throw new EmailException("Error sending the email: " + e.getMessage(), e);
}

    }
}
