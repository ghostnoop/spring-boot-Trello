package ru.itis.gilyazov.api.validations.validators;

import ru.itis.gilyazov.api.dto.SignUpForm;
import ru.itis.gilyazov.api.validations.annotations.PasswordsMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, SignUpForm> {

    @Override
    public boolean isValid(SignUpForm signUpForm, ConstraintValidatorContext constraintValidatorContext) {
        return signUpForm.getPasswordAgain().equals(signUpForm.getPassword());
    }
}
