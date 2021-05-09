package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.gilyazov.api.dto.CardDto;
import ru.itis.gilyazov.api.dto.ToDoDto;
import ru.itis.gilyazov.api.dto.ToDoForm;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.CardService;
import ru.itis.gilyazov.api.services.ToDoService;
import ru.itis.gilyazov.web.exceptions.ToDoNotFoundException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ToDoCreateController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/todo/{id}/create")
    public String getTodoCreatePage(@PathVariable Long id, Model model, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");

        CardDto cardDto = cardService.cardById(id);

        if (cardDto.getUsers().contains(user)) {
            model.addAttribute("card", cardDto);
            return "todo_create";
        }

        return "redirect:/";
    }

    @PostMapping("/todo/{id}/create")
    public String todoCreate(@PathVariable Long id, @Valid ToDoForm toDoForm, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");

        CardDto cardDto = cardService.cardById(id);

        if (cardDto.getUsers().contains(user)) {
            toDoService.save(id, toDoForm);
            return "redirect:/card/" + id;
        }

        return "redirect:/";
    }
}
