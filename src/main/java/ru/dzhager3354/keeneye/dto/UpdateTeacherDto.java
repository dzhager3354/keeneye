package ru.dzhager3354.keeneye.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTeacherDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
}
