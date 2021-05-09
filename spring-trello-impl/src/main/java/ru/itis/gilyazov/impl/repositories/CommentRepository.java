package ru.itis.gilyazov.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gilyazov.impl.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
