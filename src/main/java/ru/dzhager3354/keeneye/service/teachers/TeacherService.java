package ru.dzhager3354.keeneye.service.teachers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.exceptions.UserNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentGroupRepository;
import ru.dzhager3354.keeneye.repository.StudentRepository;
import ru.dzhager3354.keeneye.repository.TeacherRepository;
import ru.dzhager3354.keeneye.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;

    public List<StudentGroup> getAllGroups() {
        return getTeacher()
                .getStudentGroups();
    }

    public Student updateStudent(UpdateStudentDto dto) {
        Student student = studentRepository
                .findById(dto.getId())
                .orElseThrow(UserNotFoundException::new);

        if (!Objects.equals(student.getStudentGroup().getTeacher().getId(), getTeacher().getId())) {
            throw new AccessDeniedException("Student not in teacher groups");
        }

        if (!Objects.equals(dto.getId(), student.getId())) {
            throw new AccessDeniedException("Teacher cannot edit student group");
        }

        student.setSurname(dto.getSurname());
        student.setName(dto.getName());
        student.setPatronymic(dto.getPatronymic());
        student.setPhone(dto.getPhone());
        student.setStudentGroup(studentGroupRepository.findById(dto.getGroupId()).get());

        return studentRepository.save(student);
    }

    private Teacher getTeacher() {
        return userRepository.findByUsername(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                )
                .orElseThrow(UserNotFoundException::new)
                .getTeacher();
    }
}
