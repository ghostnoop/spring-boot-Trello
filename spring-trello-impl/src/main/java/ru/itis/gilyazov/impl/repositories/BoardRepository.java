package ru.itis.gilyazov.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gilyazov.api.dto.BoardDto;
import ru.itis.gilyazov.impl.entity.Board;
import ru.itis.gilyazov.impl.entity.User;

import java.util.Set;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Set<Board> findAllByUsersContaining(User user);
    Boolean existsByIdAndUsersContaining(Long id, User user);
}
