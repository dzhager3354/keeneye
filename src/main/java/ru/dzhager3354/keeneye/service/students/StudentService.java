package ru.dzhager3354.keeneye.service.students;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.entity.User;
import ru.dzhager3354.keeneye.exceptions.UserNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentGroupRepository;
import ru.dzhager3354.keeneye.repository.StudentRepository;
import ru.dzhager3354.keeneye.repository.UserRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {
    private final StudentServiceByRole service;

    public List<Student> getAllStudents(AppUserDetails details) {
        return service.getAllStudents(details);
    }

    public Student createStudent(CreateStudentDto dto, AppUserDetails details) {
        return service.createStudent(dto, details);
    }

    public Student getStudentById(Long id, AppUserDetails details) {
        return service.getStudentById(id, details);
    }

    public Student updateStudent(UpdateStudentDto student, AppUserDetails details) {
        return service.updateStudent(student, details);
    }

    public void deleteStudentById(Long id, AppUserDetails details) {
        service.deleteStudentById(id, details);
    }
}
