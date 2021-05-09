package ru.itis.gilyazov.api.services;

import ru.itis.gilyazov.api.dto.ToDoDto;
import ru.itis.gilyazov.api.dto.ToDoForm;

import java.util.Optional;
import java.util.Set;

public interface ToDoService {
    Optional<ToDoDto> save(Long id, ToDoForm toDoForm);
    void delete(Long id);
    Optional<ToDoDto> update(Long id, ToDoForm toDoForm);
    Optional<ToDoDto> findById(Long id);
    Set<ToDoDto> findAll();
}
