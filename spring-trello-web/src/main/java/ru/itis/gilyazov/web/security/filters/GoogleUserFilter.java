package ru.itis.gilyazov.web.security.filters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import ru.itis.gilyazov.api.dto.SignUpForm;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.SignUpService;
import ru.itis.gilyazov.api.services.UserService;
import ru.itis.gilyazov.impl.entity.User;
import ru.itis.gilyazov.web.security.details.UserDetailsImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

public class GoogleUserFilter extends GenericFilter {

    private final SignUpService signUpService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public GoogleUserFilter(SignUpService signUpService, UserService userService, ModelMapper modelMapper) {
        this.signUpService = signUpService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        if (authentication != null && session.getAttribute("user") == null) {
            UserDto userDto = new UserDto();
            if (authentication instanceof OAuth2AuthenticationToken){
                DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
                Map<String, Object> attributes = user.getAttributes();

                SignUpForm signUpForm = SignUpForm.builder()
                        .email((String) attributes.get("email"))
                        .firstname((String) attributes.get("given_name"))
                        .build();

                Optional<UserDto> optionalUserDto = userService.userByEmail(signUpForm.getEmail());
                userDto = optionalUserDto.orElseGet(() -> signUpService.signUp(signUpForm));

            } else if (authentication.getPrincipal() instanceof UserDetailsImpl) {
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                User user = userDetails.getUser();

                userDto = modelMapper.map(user, UserDto.class);
            }

            if (userDto != null) {
                session.setAttribute("user", userDto);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
