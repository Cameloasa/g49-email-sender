package se.lexicon.g49emailsender.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private String id;

    @Column(name = "_from" , nullable = false)
    private String from;

    @Column(name = "_to" , nullable = false)
    private String to;


    private String subject;

    @Lob
    @Column(length = 65000)
    private String content;

    @Lob
    private List<String> attachments;


    private LocalDateTime dateTime;


    private Integer type;


    @PrePersist
    public void initiateDate(){
        dateTime = LocalDateTime.now();
    }



}
