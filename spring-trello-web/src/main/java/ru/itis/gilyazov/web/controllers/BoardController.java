package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.BoardService;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/{id}")
    public String getBoardPage(@PathVariable Long id, Model model, HttpSession session) {
        BoardDto boardDto = boardService.boardById(id);
        UserDto userDto = (UserDto) session.getAttribute("user");

        if (boardService.isUserBoard(userDto, boardDto)) {
            model.addAttribute("board", boardDto);

            return "board";
        }

        return "redirect:/boards";
    }
}
