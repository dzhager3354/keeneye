package ru.dzhager3354.keeneye.service.teachers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateTeacherDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateTeacherDto;
import ru.dzhager3354.keeneye.entity.Role;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.exceptions.AccessException;
import ru.dzhager3354.keeneye.exceptions.UserNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentGroupRepository;
import ru.dzhager3354.keeneye.repository.StudentRepository;
import ru.dzhager3354.keeneye.repository.TeacherRepository;
import ru.dzhager3354.keeneye.repository.UserRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TeacherService implements ITeacherService {
    private final TeacherServiceForAdmin adminService;
    private final TeacherServiceForTeacher teacherService;
    private final TeacherServiceForStudent studentService;

    @Override
    public List<Teacher> getTeachers(AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_STUDENT -> studentService.getTeachers(details);
            case ROLE_ADMIN -> adminService.getTeachers(details);
            case ROLE_TEACHER -> teacherService.getTeachers(details);
        };
    }

    @Override
    public Teacher createTeacher(CreateTeacherDto dto, AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_ADMIN -> adminService.createTeacher(dto, details);
            default -> throw new AccessException();
        };
    }

    @Override
    public Teacher updateTeacher(UpdateTeacherDto dto, AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_ADMIN -> adminService.updateTeacher(dto, details);
            case ROLE_TEACHER -> teacherService.updateTeacher(dto, details);
            default -> throw new AccessException();
        };
    }

    @Override
    public void deleteTeacherById(Long id, AppUserDetails details) {
        if (Objects.requireNonNull(details.getRole()) == Role.ROLE_ADMIN) {
            adminService.deleteTeacherById(id, details);
        } else {
            throw new AccessException();
        }
    }
}
