package ru.itis.gilyazov.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardForm {

    private UserDto owner;
    private String title;
    private Set<Long> users;
}
