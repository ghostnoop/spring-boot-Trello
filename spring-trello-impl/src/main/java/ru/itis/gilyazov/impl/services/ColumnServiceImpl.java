package ru.itis.gilyazov.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.gilyazov.api.dto.ColumnCreateForm;
import ru.itis.gilyazov.api.dto.ColumnDto;
import ru.itis.gilyazov.api.services.ColumnService;
import ru.itis.gilyazov.impl.entity.Board;
import ru.itis.gilyazov.impl.entity.Column;
import ru.itis.gilyazov.impl.repositories.BoardRepository;
import ru.itis.gilyazov.impl.repositories.ColumnRepository;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createColumn(ColumnCreateForm columnCreateForm) {
        Board board = boardRepository.findById(columnCreateForm.getBoardId()).orElseThrow(IllegalArgumentException::new);

        Column column = Column.builder()
                .title(columnCreateForm.getTitle())
                .board(board)
                .build();

        columnRepository.save(column);

        board.getColumns().add(column);

        boardRepository.save(board);
    }

    @Override
    public ColumnDto columnById(Long id) {
        return columnRepository.findById(id)
                .map(column -> modelMapper.map(column, ColumnDto.class))
                .orElseThrow(IllegalArgumentException::new);
    }
}
