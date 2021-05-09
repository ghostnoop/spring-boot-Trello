package ru.itis.gilyazov.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    public boolean isAdmin() {
        return this.role == Role.ROLE_ADMIN;
    }
}
