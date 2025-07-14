package ru.dzhager3354.keeneye.service.students;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.exceptions.AccessException;
import ru.dzhager3354.keeneye.exceptions.StudentNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceForTeacher implements IStudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents(AppUserDetails details) {
        return details.getUser()
                .getTeacher()
                .getStudentGroups()
                .stream()
                .flatMap(studentGroup -> studentGroup.getStudents().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Student createStudent(CreateStudentDto dto, AppUserDetails details) {
        throw new AccessException();
    }

    @Override
    public Student getStudentById(Long id, AppUserDetails details) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Student updateStudent(UpdateStudentDto dto, AppUserDetails details) {
        Student student = studentRepository
                .findById(dto.getId())
                .orElseThrow(StudentNotFoundException::new);

        Teacher teacher = details.getUser().getTeacher();

        if (!Objects.equals(student.getStudentGroup().getTeacher().getId(), teacher.getId())) {
            throw new AccessDeniedException("Student not in teacher groups");
        }

        return studentRepository.save(
                Student.builder()
                        .id(dto.getId())
                        .user(student.getUser())
                        .surname(dto.getSurname())
                        .name(dto.getName())
                        .patronymic(dto.getPatronymic())
                        .phone(dto.getPhone())
                        .studentGroup(student.getStudentGroup())
                        .build()
        );
    }

    @Override
    public void deleteStudentById(Long id, AppUserDetails details) {
        throw new AccessException();
    }
}
