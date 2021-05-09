package ru.itis.gilyazov.api.services;

import ru.itis.gilyazov.api.dto.UserDto;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    Optional<UserDto> userByEmail(String email);
    Set<UserDto> allUsers();

    Optional<UserDto> userById(Long id);
}
