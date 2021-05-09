package ru.itis.gilyazov.api.services;

import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.BoardForm;
import ru.itis.gilyazov.api.dto.UserDto;

import java.util.Set;

public interface BoardService {
    BoardDto createBoard(BoardForm boardForm);
    Set<BoardDto> userBoards(UserDto userDto);
    Boolean isUserBoard(UserDto userDto, BoardDto boardDto);
    BoardDto boardById(Long id);
}
