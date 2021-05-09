package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.CardDto;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.CardService;

import javax.servlet.http.HttpSession;

@Controller
public class CardArchivedController {

    @Autowired
    private CardService cardService;

    @GetMapping("/card/{id}/archived")
    public String archivedCard(@PathVariable Long id, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        CardDto cardDto = cardService.cardById(id);

        if (cardDto.getUsers().contains(userDto)) {
            cardService.archivedCard(cardDto);
        }
        
        return "redirect:/boards";
    }
}
