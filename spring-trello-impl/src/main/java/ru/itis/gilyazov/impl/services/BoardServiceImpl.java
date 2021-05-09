package ru.itis.gilyazov.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.api.dto.BoardForm;
import ru.itis.gilyazov.api.dto.UserDto;
import ru.itis.gilyazov.api.services.BoardService;
import ru.itis.gilyazov.impl.aspect.Logging;
import ru.itis.gilyazov.impl.entity.Board;
import ru.itis.gilyazov.impl.entity.User;
import ru.itis.gilyazov.impl.repositories.BoardRepository;
import ru.itis.gilyazov.impl.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BoardDto createBoard(BoardForm boardForm) {
        Set<User> users = boardForm.getUsers().stream()
                .map(id -> userRepository.findById(id).orElseThrow(IllegalArgumentException::new))
                .collect(Collectors.toSet());

        Board board = Board.builder()
                .columns(new HashSet<>())
                .owner(modelMapper.map(boardForm.getOwner(), User.class))
                .title(boardForm.getTitle())
                .users(users)
                .build();

        return modelMapper.map(boardRepository.saveAndFlush(board), BoardDto.class);
    }

    @Override
    @Logging
    public Set<BoardDto> userBoards(UserDto userDto) {
        return boardRepository.findAllByUsersContaining(modelMapper.map(userDto, User.class))
                .stream().map(board -> modelMapper.map(board, BoardDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Boolean isUserBoard(UserDto userDto, BoardDto boardDto) {
        User user = modelMapper.map(userDto, User.class);
        Board board = modelMapper.map(boardDto, Board.class);

        return boardRepository.existsByIdAndUsersContaining(board.getId(), user);
    }

    @Override
    public BoardDto boardById(Long id) {
        return boardRepository.findById(id)
                .map(board -> modelMapper.map(board, BoardDto.class))
                .orElseThrow(IllegalArgumentException::new);
    }
}
