package ru.itis.gilyazov.web.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.gilyazov.api.dto.ToDoDto;
import ru.itis.gilyazov.api.dto.ToDoForm;
import ru.itis.gilyazov.api.dto.ToDoRest;
import ru.itis.gilyazov.api.services.ToDoService;
import ru.itis.gilyazov.web.exceptions.CardNotFoundException;
import ru.itis.gilyazov.web.exceptions.ToDoNotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoRestController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Getting todo by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = ToDoRest.class)})
    @GetMapping("/{id}")
    public ResponseEntity<ToDoRest> toDoById(@PathVariable Long id) throws ToDoNotFoundException {
        return ResponseEntity.ok(modelMapper.map(toDoService.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("toDo not found")), ToDoRest.class));
    }

    @ApiOperation(value = "Delete todo by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        toDoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update todo by id and new ToDoForm")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = ToDoRest.class)})
    @PutMapping("/{id}")
    public ResponseEntity<ToDoRest> updateById(@PathVariable Long id, @RequestBody @Valid ToDoForm toDoForm) throws ToDoNotFoundException {
        return ResponseEntity.ok(modelMapper.map(toDoService.update(id, toDoForm)
                .orElseThrow(() -> new ToDoNotFoundException("toDo not found")), ToDoRest.class));
    }

    @ApiOperation(value = "Create new Todo by card id and ToDoForm")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = ToDoRest.class)})
    @PostMapping("/{card_id}")
    public ResponseEntity<ToDoRest> createTodo(@PathVariable(name = "card_id") Long id, @RequestBody @Valid ToDoForm toDoForm) throws CardNotFoundException {
        return ResponseEntity.ok(modelMapper.map(toDoService.save(id, toDoForm)
            .orElseThrow(() -> new CardNotFoundException("card not found")), ToDoRest.class));
    }
}
