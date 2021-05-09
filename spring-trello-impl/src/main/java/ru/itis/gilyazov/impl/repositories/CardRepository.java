package ru.itis.gilyazov.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gilyazov.impl.entity.Card;

import java.util.Set;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
