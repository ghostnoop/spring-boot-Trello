package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.CardDto;
import ru.itis.gilyazov.api.dto.ColumnDto;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.BoardService;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
public class ArchivedCardsController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/{id}/archived/cards")
    public String getArchivedCardsPage(@PathVariable Long id, Model model, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");

        Set<CardDto> cards = new HashSet<>();

        BoardDto board = boardService.boardById(id);

        if (board.getUsers().contains(userDto)) {

            for (ColumnDto columnDto : board.getColumns()) {
                cards.addAll(columnDto.getCards());
            }

            model.addAttribute("cards", cards);

            return "archived_cards";
        }

        return "redirect:/";
    }
}
