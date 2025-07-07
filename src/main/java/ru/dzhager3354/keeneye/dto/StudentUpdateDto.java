package ru.dzhager3354.keeneye.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private Long groupId;
}
