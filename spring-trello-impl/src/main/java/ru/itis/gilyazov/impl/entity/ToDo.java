package ru.itis.gilyazov.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Temporal(value = TemporalType.DATE)
    private Date expiredDate;
    private Boolean state;

    @ManyToOne
    private Card card;

    @Override
    public int hashCode() {
        return Objects.hash(id, description, expiredDate, state);
    }
}
