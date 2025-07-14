package ru.dzhager3354.keeneye.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {
    private Long userId;
    @NotBlank
    private String surname;
    @NotBlank
    private String name;
    private String patronymic;
    @Nullable
    private Long group;
    @NotBlank
    private String phone;
}
