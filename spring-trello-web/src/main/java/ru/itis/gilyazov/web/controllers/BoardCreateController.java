package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.BoardForm;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.BoardService;
import ru.itis.gilyazov.api.services.UserService;
import ru.itis.gilyazov.impl.entity.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class BoardCreateController {

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/create")
    public String boardCreatePage(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        model.addAttribute("users", userService.allUsers());
        return "board_create";
    }

    @PostMapping("/board/create")
    public String newBoard(BoardForm boardForm, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        boardForm.setOwner(userDto);
        if (boardForm.getUsers() == null) {
            boardForm.setUsers(new HashSet<>());
        }
        boardForm.getUsers().add(userDto.getId());
        
        boardService.createBoard(boardForm);
        return "redirect:/";
    }
}
