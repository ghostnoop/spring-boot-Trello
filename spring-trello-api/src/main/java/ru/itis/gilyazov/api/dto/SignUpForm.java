package ru.itis.gilyazov.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.gilyazov.api.validations.annotations.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordsMatch
public class SignUpForm {

    @Size(min=3, max=15, message = "The name is too short or too long")
    private String firstname;
    @Email(message = "Email incorrect")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "The password must contain at least 8" +
            " characters and include uppercase Latin letters and numbers")
    private String password;
    private String passwordAgain;

}
