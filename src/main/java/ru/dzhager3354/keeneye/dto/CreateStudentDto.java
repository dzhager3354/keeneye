package ru.dzhager3354.keeneye.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String surname;
    @NotBlank
    private String name;
    private String patronymic;
    private String group;
    @NotBlank
    private String phone;
}
