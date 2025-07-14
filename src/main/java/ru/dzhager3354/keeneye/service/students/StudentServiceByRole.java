package ru.dzhager3354.keeneye.service.students;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Role;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentServiceByRole implements IStudentService {
    private final StudentServiceForStudent studentService;
    private final StudentServiceForTeacher teacherService;
    private final StudentServiceForAdmin adminService;

    @Override
    public List<Student> getAllStudents(AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_STUDENT -> studentService.getAllStudents(details);
            case ROLE_TEACHER -> teacherService.getAllStudents(details);
            case ROLE_ADMIN -> adminService.getAllStudents(details);
            default -> throw new AccessDeniedException("No role");
        };
    }

    @Override
    public Student createStudent(CreateStudentDto dto, AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_ADMIN -> adminService.createStudent(dto, details);
            default -> throw new AccessDeniedException("Denied");
        };
    }

    @Override
    public Student getStudentById(Long id, AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_STUDENT -> studentService.getStudentById(id, details);
            case ROLE_TEACHER -> teacherService.getStudentById(id, details);
            case ROLE_ADMIN -> adminService.getStudentById(id, details);
            default -> throw new AccessDeniedException("No role");
        };
    }

    @Override
    public Student updateStudent(UpdateStudentDto dto, AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_STUDENT -> studentService.updateStudent(dto, details);
            case ROLE_TEACHER -> teacherService.updateStudent(dto, details);
            case ROLE_ADMIN -> adminService.updateStudent(dto, details);
            default -> throw new AccessDeniedException("No role");
        };
    }

    @Override
    public void deleteStudentById(Long id, AppUserDetails details) {
        if (Objects.requireNonNull(details.getRole()) == Role.ROLE_ADMIN) {
            adminService.deleteStudentById(id, details);
        } else {
            throw new AccessDeniedException("Denied");
        }
    }
}
