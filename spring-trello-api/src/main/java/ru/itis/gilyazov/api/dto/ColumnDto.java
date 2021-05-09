package ru.itis.gilyazov.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColumnDto {

    private Long id;

    private String title;

    private BoardDto board;

    private Set<CardDto> cards;

    @Override
    public int hashCode() {
        return Objects.hash(id, title, cards);
    }

    @Override
    public String toString() {
        return "ColumnDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", board=" + board.getTitle() +
                ", cards=" + cards +
                '}';
    }
}
