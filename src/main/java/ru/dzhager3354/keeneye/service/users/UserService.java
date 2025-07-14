package ru.dzhager3354.keeneye.service.users;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateUserDto;
import ru.dzhager3354.keeneye.entity.User;
import ru.dzhager3354.keeneye.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public User createUser(CreateUserDto dto) {
        return repository.save(
                User.builder()
                        .username(dto.getUsername())
                        .password(encoder.encode(dto.getPassword()))
                        .userRole(dto.getRole())
                        .build()
        );
    }
}
