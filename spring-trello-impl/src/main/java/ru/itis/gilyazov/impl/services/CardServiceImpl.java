package ru.itis.gilyazov.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.gilyazov.api.dto.CardCreateForm;
import ru.itis.gilyazov.api.dto.CardDto;
import ru.itis.gilyazov.api.services.CardService;
import ru.itis.gilyazov.impl.entity.*;
import ru.itis.gilyazov.impl.repositories.*;

import java.io.FileReader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CardDto cardById(Long id) {
        return cardRepository.findById(id)
                .map(card -> modelMapper.map(card, CardDto.class))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void createCard(CardCreateForm cardCreateForm) {
        Set<User> users = cardCreateForm.getUsers().stream()
                .map(id -> userRepository.findById(id).orElseThrow(IllegalArgumentException::new))
                .collect(Collectors.toSet());

        Column column = columnRepository.findById(cardCreateForm.getColumnId()).orElseThrow(IllegalArgumentException::new);

        Card card = Card.builder()
                .title(cardCreateForm.getTitle())
                .description(cardCreateForm.getDescription())
                .column(column)
                .users(users)
                .build();

        cardRepository.save(card);

        column.getCards().add(card);

        columnRepository.save(column);
    }

    @Override
    public void archivedCard(CardDto cardDto) {
        Card card = cardRepository.findById(cardDto.getId())
            .orElseThrow(IllegalArgumentException::new);

        card.setIsDeleted(true);

        cardRepository.save(card);
    }

    @Override
    public void unarchivedCard(CardDto cardDto) {
        Card card = cardRepository.findById(cardDto.getId())
                .orElseThrow(IllegalArgumentException::new);

        card.setIsDeleted(false);

        cardRepository.save(card);
    }

    @Override
    public void updateCard(CardDto cardDto) {
        Card card = cardRepository.findById(cardDto.getId())
                .orElseThrow(IllegalArgumentException::new);

        if (cardDto.getFiles() != null) {
            Set<File> files = cardDto.getFiles()
                    .stream().map(fileDto -> {
                        File file = modelMapper.map(fileDto, File.class);
                        fileRepository.save(file);
                        return file;
                    })
                    .collect(Collectors.toSet());

            card.setFiles(files);
        }

        if (cardDto.getComments() != null) {
            Set<Comment> comments = cardDto.getComments()
                    .stream().map(commentDto -> {
                        Comment comment = modelMapper.map(commentDto, Comment.class);
                        commentRepository.save(comment);
                        return comment;
                    })
                    .collect(Collectors.toSet());

            card.setComments(comments);
        }

        cardRepository.save(card);
    }
}
