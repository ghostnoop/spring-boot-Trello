package ru.itis.gilyazov.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.gilyazov.impl.entity.Column;

public interface ColumnRepository extends JpaRepository<Column, Long> {

}
