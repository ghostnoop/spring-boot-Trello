package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.CardCreateForm;
import ru.itis.gilyazov.api.dto.ColumnDto;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.CardService;
import ru.itis.gilyazov.api.services.ColumnService;

import javax.servlet.http.HttpSession;
import java.util.HashSet;

@Controller
public class CardCreateController {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private CardService cardService;

    @GetMapping("/column/{id}/card/create")
    public String cardCreatePage(@PathVariable Long id, Model model) {
        ColumnDto columnDto = columnService.columnById(id);

        model.addAttribute("column", columnDto);

        return "card_create";
    }

    @PostMapping("/column/card/create")
    public String cardCreate(CardCreateForm form, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        ColumnDto columnDto = columnService.columnById(form.getColumnId());

        if (form.getUsers() == null) {
            form.setUsers(new HashSet<>());
        }
        form.getUsers().add(userDto.getId());

        if (columnDto.getBoard().getUsers().contains(userDto)) {
            cardService.createCard(form);
        }
        return "redirect:/board/" + columnDto.getBoard().getId();
    }
}
