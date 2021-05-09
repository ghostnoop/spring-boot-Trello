package ru.itis.gilyazov.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.UserService;
import ru.itis.gilyazov.impl.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<UserDto> userByEmail(String email) {
        return userRepository.findByEmail(email)
                .map((user) -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Set<UserDto> allUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<UserDto> userById(Long id) {
        return userRepository.findById(id)
                .map((user) -> modelMapper.map(user, UserDto.class));
    }
}
