package ru.itis.gilyazov.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDto {

    private Long id;
    private String description;
    private Boolean state;
    private Date expiredDate;
    private CardDto card;

    @Override
    public int hashCode() {
        return Objects.hash(id, description, state, expiredDate);
    }

    @Override
    public String toString() {
        return "ToDoDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", expiredDate=" + expiredDate +
                ", card=" + card.getTitle() +
                '}';
    }
}
