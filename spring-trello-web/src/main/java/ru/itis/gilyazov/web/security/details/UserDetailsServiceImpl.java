package ru.itis.gilyazov.web.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.gilyazov.impl.entity.User;
import ru.itis.gilyazov.impl.repositories.UserRepository;

@Component("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserDetailsImpl(user);
    }
}
