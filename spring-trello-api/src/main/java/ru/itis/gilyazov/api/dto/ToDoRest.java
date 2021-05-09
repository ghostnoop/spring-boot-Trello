package ru.itis.gilyazov.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoRest {

    private Long id;
    private String description;
    private Boolean state;
    private Date expiredDate;
}
