package se.lexicon.g49emailsender.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorDTO {

    private Integer errorCode;
    private String errorMessage;
    private LocalDateTime dateTime = LocalDateTime.now();


    public ErrorDTO(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
