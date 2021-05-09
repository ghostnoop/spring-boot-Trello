package ru.itis.gilyazov.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Type(type = "text")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users;

    @ManyToOne
    private Column column;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "card")
    private Set<ToDo> todos;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<File> files;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @Builder.Default
    private Boolean isDeleted = false;
}
