package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.ColumnCreateForm;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.BoardService;
import ru.itis.gilyazov.api.services.ColumnService;

import javax.servlet.http.HttpSession;

@Controller
public class ColumnCreateController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ColumnService columnService;

    @GetMapping("/board/{id}/column/create")
    public String cardCreatePage(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.boardById(id));

        return "column_create";
    }

    @PostMapping("/board/column/create")
    public String cardCreate(ColumnCreateForm columnCreateForm, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        BoardDto boardDto = boardService.boardById(columnCreateForm.getBoardId());

        if (boardDto.getUsers().contains(userDto)) {
            columnService.createColumn(columnCreateForm);
        }
        return "redirect:/board/" + columnCreateForm.getBoardId();
    }
}
