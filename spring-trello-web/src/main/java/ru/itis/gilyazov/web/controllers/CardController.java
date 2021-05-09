package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.gilyazov.api.services.CardService;

@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/card/{id}")
    public String getCardPage(@PathVariable Long id, Model model) {
        model.addAttribute("card", cardService.cardById(id));

        System.out.println(cardService.cardById(id));
        return "card";
    }
}
