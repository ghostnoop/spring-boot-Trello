package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.gilyazov.api.dto.*;
import ru.itis.gilyazov.api.services.CardService;
import ru.itis.gilyazov.web.utils.FileSaver;

import javax.servlet.http.HttpSession;

@Controller
public class CardCommentController {

    @Autowired
    private CardService cardService;

    @PostMapping("/card/{id}/comment")
    public String newComment(@PathVariable Long id, CommentForm commentForm, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");

        CardDto cardDto = cardService.cardById(id);

        if (cardDto.getUsers().contains(userDto)) {
            CommentDto commentDto = CommentDto.builder()
                    .text(commentForm.getText())
                    .user(userDto)
                    .build();

            cardDto.getComments().add(commentDto);

            cardService.updateCard(cardDto);
        }

        return "redirect:/card/" + id;
    }
}
