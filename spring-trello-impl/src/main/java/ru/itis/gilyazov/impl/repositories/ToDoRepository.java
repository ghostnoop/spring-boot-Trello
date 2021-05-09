package ru.itis.gilyazov.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gilyazov.impl.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
