package ru.dzhager3354.keeneye.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dzhager3354.keeneye.entity.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {
    private String username;
    private String password;
    private Role role;
}
