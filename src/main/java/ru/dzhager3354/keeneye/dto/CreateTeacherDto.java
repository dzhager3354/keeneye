package ru.dzhager3354.keeneye.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTeacherDto {
    private String surname;
    private String name;
    private String patronymic;
    private Long userId;
}
