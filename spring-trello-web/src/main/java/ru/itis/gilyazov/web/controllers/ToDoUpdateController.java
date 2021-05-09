package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.gilyazov.api.dto.CardDto;
import ru.itis.gilyazov.api.dto.ToDoDto;
import ru.itis.gilyazov.api.dto.ToDoForm;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.CardService;
import ru.itis.gilyazov.api.services.ToDoService;
import ru.itis.gilyazov.web.exceptions.ToDoNotFoundException;

import javax.servlet.http.HttpSession;

@Controller
public class ToDoUpdateController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private CardService cardService;

    @GetMapping("/todo/{id}/update")
    public String updateToDo(@PathVariable Long id, HttpSession session) throws ToDoNotFoundException {
        ToDoDto toDoDto = toDoService.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException(""));

        UserDto user = (UserDto) session.getAttribute("user");

        if (toDoDto.getCard().getUsers().contains(user)) {
            ToDoForm toDoForm = ToDoForm.builder()
                    .description(toDoDto.getDescription())
                    .expiredDate(toDoDto.getExpiredDate())
                    .state(!toDoDto.getState())
                    .build();

            toDoService.update(id, toDoForm);
        }

        return "redirect:/card/" + toDoDto.getCard().getId();
    }
}
