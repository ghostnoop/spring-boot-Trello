package ru.itis.gilyazov.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gilyazov.impl.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
