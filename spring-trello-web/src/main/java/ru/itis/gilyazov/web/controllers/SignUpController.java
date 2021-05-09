package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.gilyazov.api.dto.SignUpForm;
import ru.itis.gilyazov.api.services.SignUpService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    @Autowired
    private  SignUpService signUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signUp")
    public String getSignUpPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("signUp", "");
        model.addAttribute("signUpForm", new SignUpForm());

        if (error != null) {
            model.addAttribute("error", "User with that email already exist");
        }

        return "sign_up";
    }

    @PermitAll
    @PostMapping("/signUp")
    public String signUp(@Valid SignUpForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("PasswordsMatch.signUpForm")) {
                    model.addAttribute("passwordsErrorMessage", error.getDefaultMessage());
                }
            });

            model.addAttribute("signUpForm", form);
            return "sign_up";
        }

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        if (signUpService.signUp(form) != null) {
            return "redirect:/signIn";
        }

        return "redirect:/signUp?error";

    }
}
