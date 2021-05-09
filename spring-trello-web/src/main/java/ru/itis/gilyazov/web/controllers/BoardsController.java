package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.BoardService;
import ru.itis.gilyazov.api.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class BoardsController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"/", "/boards"})
    public String getMainPage(HttpSession session, Model model) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        System.out.println(userDto);
        Set<BoardDto> boards = boardService.userBoards(userDto);

        model.addAttribute("boards", boards);
        return "boards";
    }
}
