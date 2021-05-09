package ru.itis.gilyazov.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "columns")
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Board board;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "column")
    private Set<Card> cards;

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
