package ru.itis.gilyazov.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.gilyazov.api.dto.CardDto;
import ru.itis.gilyazov.api.dto.FileDto;
import ru.itis.gilyazov.api.dto.FileForm;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.CardService;
import ru.itis.gilyazov.web.utils.FileSaver;

import javax.servlet.http.HttpSession;
import java.io.FileDescriptor;

@Controller
public class CardFileUpload {

    @Autowired
    private CardService cardService;

    @Value("${upload.dir.image}")
    private String uploadDir;

    @PostMapping("/card/{id}/file")
    public String uploadFile(@PathVariable Long id, FileForm fileForm, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");

        CardDto cardDto = cardService.cardById(id);

        if (cardDto.getUsers().contains(userDto)) {
            String filename = FileSaver.save(fileForm.getFile(), uploadDir);

            FileDto fileDto = FileDto.builder()
                    .filename(fileForm.getFile().getOriginalFilename())
                    .savedFilename(filename)
                    .build();

            cardDto.getFiles().add(fileDto);

            cardService.updateCard(cardDto);
        }

        return "redirect:/card/" + id;
    }
}
