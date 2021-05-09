package ru.itis.gilyazov.api.services;

import ru.itis.gilyazov.api.dto.CardCreateForm;
import ru.itis.gilyazov.api.dto.CardDto;

public interface CardService {
    CardDto cardById(Long id);
    void createCard(CardCreateForm cardCreateForm);

    void archivedCard(CardDto cardDto);

    void unarchivedCard(CardDto cardDto);

    void updateCard(CardDto cardDto);
}
