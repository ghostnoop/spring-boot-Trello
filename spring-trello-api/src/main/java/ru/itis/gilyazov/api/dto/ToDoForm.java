package ru.itis.gilyazov.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoForm {

    @Size(min = 3, message = "description can't be tiny that 4")
    private String description;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiredDate;
    @NotNull
    @Builder.Default
    private Boolean state = true;
}
