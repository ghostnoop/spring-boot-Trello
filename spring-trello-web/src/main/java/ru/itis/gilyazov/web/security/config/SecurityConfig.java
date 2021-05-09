package ru.itis.gilyazov.web.security.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.itis.gilyazov.api.services.SignUpService;
import ru.itis.gilyazov.api.services.UserService;
import ru.itis.gilyazov.web.security.filters.GoogleUserFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/api/**", "/swagger-ui.html/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringAntMatchers("/api/**").and()
                .authorizeRequests()
                    .antMatchers("/signIn", "/signUp", "/logout").permitAll()
                    .antMatchers("/css/**", "/dist/**", "/api/**").permitAll()
                    .anyRequest().authenticated().and()
                .formLogin()
                    .loginPage("/signIn")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/boards")
                    .failureUrl("/signIn?error").and()
                .oauth2Login().
                    defaultSuccessUrl("/boards").and()
                .addFilterAfter(new GoogleUserFilter(signUpService, userService, modelMapper), UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .logoutSuccessUrl("/signIn")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
