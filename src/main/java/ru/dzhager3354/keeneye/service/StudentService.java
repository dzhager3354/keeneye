package ru.dzhager3354.keeneye.service;

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

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentGroupRepository studentGroupRepository;

    public List<Student> getAllStudents() {
        User user = userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(UserNotFoundException::new);
        return user.getStudent().getStudentGroup().getStudents();
    }

    public Student createStudent(CreateStudentDto dto) {
        User user = userRepository.getReferenceById(dto.getUserId());
        StudentGroup group = studentGroupRepository.getReferenceById(dto.getGroup());
        Student newStudent = Student.builder()
                .surname(dto.getSurname())
                .name(dto.getName())
                .patronymic(dto.getPatronymic())
                .phone(dto.getPhone())
                .user(user)
                .studentGroup(group)
                .build();
        return studentRepository.save(newStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student updateStudent(UpdateStudentDto student) {
        if (student.getId() == null) {
            throw new RuntimeException();
        }
        Student tokenStudent = userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(UserNotFoundException::new).getStudent();

        if (!Objects.equals(tokenStudent.getId(), student.getId())) {
            throw new AccessDeniedException("No access");
        }

        tokenStudent.setSurname(student.getSurname());
        tokenStudent.setName(tokenStudent.getName());
        tokenStudent.setPatronymic(tokenStudent.getPatronymic());
        tokenStudent.setPhone(student.getPhone());
        tokenStudent.setStudentGroup(studentGroupRepository.getReferenceById(student.getGroupId()));

        return studentRepository.save(tokenStudent);
    }

    public void deleteStudentById(Long id) {
        if (id == null) {
            throw new RuntimeException();
        }
        studentRepository.deleteById(id);
    }
}
