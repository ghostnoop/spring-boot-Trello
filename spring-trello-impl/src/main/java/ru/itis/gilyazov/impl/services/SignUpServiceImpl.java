package ru.itis.gilyazov.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.gilyazov.api.dto.SignUpForm;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.SignUpService;
import ru.itis.gilyazov.impl.entity.User;
import ru.itis.gilyazov.impl.repositories.UserRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto signUp(SignUpForm signUpForm) {
        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            return null;
        }

        User user = User.builder()
                .email(signUpForm.getEmail())
                .firstname(signUpForm.getFirstname())
                .password(signUpForm.getPassword())
                .role(User.Role.ROLE_USER)
                .build();

        return modelMapper.map(userRepository.saveAndFlush(user), UserDto.class);
    }
}
