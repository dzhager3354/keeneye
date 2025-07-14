package ru.dzhager3354.keeneye.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentGroupDto {
    private Long id;
    private String name;
    private Long teacherId;
}
