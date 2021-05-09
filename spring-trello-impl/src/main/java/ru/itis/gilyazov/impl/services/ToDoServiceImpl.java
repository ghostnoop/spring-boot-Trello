package ru.itis.gilyazov.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.gilyazov.api.dto.ToDoDto;
import ru.itis.gilyazov.api.dto.ToDoForm;
import ru.itis.gilyazov.api.services.ToDoService;
import ru.itis.gilyazov.impl.entity.Card;
import ru.itis.gilyazov.impl.entity.ToDo;
import ru.itis.gilyazov.impl.repositories.CardRepository;
import ru.itis.gilyazov.impl.repositories.ToDoRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<ToDoDto> save(Long id, ToDoForm toDoForm) {
        Optional<Card> card = cardRepository.findById(id);

        if (!card.isPresent()) {
            return Optional.empty();
        }

        ToDo toDo = ToDo.builder()
                .card(card.get())
                .description(toDoForm.getDescription())
                .expiredDate(toDoForm.getExpiredDate())
                .state(toDoForm.getState())
                .build();

        return Optional.of(modelMapper.map(toDoRepository.save(toDo), ToDoDto.class));
    }

    @Override
    public void delete(Long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public Optional<ToDoDto> update(Long id, ToDoForm toDoForm) {
        Optional<ToDo> toDoForUpdate = toDoRepository.findById(id);

        if (!toDoForUpdate.isPresent()) {
            return Optional.empty();
        }

        ToDo toDo = toDoForUpdate.get();

        toDo.setDescription(toDoForm.getDescription());
        toDo.setExpiredDate(toDoForm.getExpiredDate());
        toDo.setState(toDoForm.getState());

        return Optional.of(modelMapper.map(toDoRepository.save(toDo), ToDoDto.class));
    }

    @Override
    public Optional<ToDoDto> findById(Long id) {
        return toDoRepository.findById(id)
                .map(toDo -> modelMapper.map(toDo, ToDoDto.class));
    }

    @Override
    public Set<ToDoDto> findAll() {
        return toDoRepository.findAll()
                .stream().map(toDo -> modelMapper.map(toDo, ToDoDto.class))
                .collect(Collectors.toSet());
    }
}
