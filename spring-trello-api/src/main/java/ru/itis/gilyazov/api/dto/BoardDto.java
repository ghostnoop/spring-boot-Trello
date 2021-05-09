package ru.itis.gilyazov.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private Long id;

    private String title;
    private Set<ColumnDto> columns;
    private UserDto owner;
    private Set<UserDto> users;
}
