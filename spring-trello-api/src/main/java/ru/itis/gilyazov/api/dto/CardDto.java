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
public class CardDto {

    private Long id;
    private String title;
    private String description;
    private Set<UserDto> users;
    private Set<ToDoDto> todos;
    private Set<FileDto> files;
    private Set<CommentDto> comments;
    private Boolean isDeleted = false;
}
