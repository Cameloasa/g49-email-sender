package se.lexicon.g49emailsender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g49emailsender.domain.dto.EmailDTO;
import se.lexicon.g49emailsender.domain.entity.Email;
import se.lexicon.g49emailsender.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }
    @Override
    public void sendEmail(EmailDTO dto) {
        //Validate the params
        if(dto == null) throw new IllegalArgumentException("DTO cannot be null");

        //convert DTO to Entity using builder
        Email emailEntity = Email.builder()
                .to(dto.getTo())
                .from("camelia1414@gmail.com")
                .subject(dto.getSubject())
                .content(dto.getHtml())
                .type(dto.getType())
                .build();

        emailRepository.save(emailEntity);

        //todo send the email


    }
}
