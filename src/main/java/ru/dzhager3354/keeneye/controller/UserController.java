package ru.dzhager3354.keeneye.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dzhager3354.keeneye.dto.CreateUserDto;
import ru.dzhager3354.keeneye.entity.User;
import ru.dzhager3354.keeneye.service.users.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    @PostMapping("/create")
    public User createUser(@RequestBody CreateUserDto dto) {
        return service.createUser(dto);
    }
}
