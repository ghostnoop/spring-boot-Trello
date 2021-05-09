package ru.itis.gilyazov.api.services;

import ru.itis.gilyazov.api.dto.ColumnCreateForm;
import ru.itis.gilyazov.api.dto.ColumnDto;

public interface ColumnService {
    void createColumn(ColumnCreateForm columnCreateForm);

    ColumnDto columnById(Long id);
}
