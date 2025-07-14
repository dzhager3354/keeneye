package ru.dzhager3354.keeneye.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dzhager3354.keeneye.dto.CreateUserDto;
import ru.dzhager3354.keeneye.entity.Role;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.entity.User;
import ru.dzhager3354.keeneye.service.groups.GroupServiceForAdmin;
import ru.dzhager3354.keeneye.service.students.StudentServiceForAdmin;
import ru.dzhager3354.keeneye.service.teachers.TeacherServiceForAdmin;
import ru.dzhager3354.keeneye.service.users.UserService;

@Component
@AllArgsConstructor
public class InitService {
    private final StudentServiceForAdmin studentServiceForAdmin;
    private final UserService userService;
    private final TeacherServiceForAdmin teacherServiceForAdmin;
    private final GroupServiceForAdmin groupServiceForAdmin;

    public void init() {
        User admin = userService.createUser(
                CreateUserDto.builder()
                        .username("admin")
                        .password("admin")
                        .role(Role.ROLE_ADMIN)
                        .build()
        );
    }
}
