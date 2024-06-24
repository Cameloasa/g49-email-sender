package se.lexicon.g49emailsender.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString


public class EmailDTO {

    @Email(message = "To field should be a valid email.")
    @NotBlank(message = "To/sender field should not be blank.")
    private String to;

    @NotBlank(message = "Must have a subject to send.")
    private String subject;

    @NotBlank(message = " HTML cannot be empty. ")
    private String html;// string body

    @Positive(message = "Type should be a positive number.")
    private Integer type;

}
