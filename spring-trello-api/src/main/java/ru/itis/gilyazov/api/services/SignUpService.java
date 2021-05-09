package ru.itis.gilyazov.api.services;

import ru.itis.gilyazov.api.dto.SignUpForm;
import ru.itis.gilyazov.api.dto.UserDto;

public interface SignUpService {
    UserDto signUp(SignUpForm signUpForm);
}
