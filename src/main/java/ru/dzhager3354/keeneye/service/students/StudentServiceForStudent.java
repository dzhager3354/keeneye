package ru.dzhager3354.keeneye.service.students;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.exceptions.AccessException;
import ru.dzhager3354.keeneye.exceptions.StudentGroupNotFoundException;
import ru.dzhager3354.keeneye.exceptions.StudentNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentGroupRepository;
import ru.dzhager3354.keeneye.repository.StudentRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentServiceForStudent implements IStudentService {
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;

    @Override
    public List<Student> getAllStudents(AppUserDetails details) {
        StudentGroup group = details.getUser().getStudent().getStudentGroup();
        if (group == null) {
            return null;
        }
        return group.getStudents();
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
        if (!Objects.equals(details.getUser().getStudent().getId(), dto.getId())) {
            throw new AccessException();
        }

        StudentGroup group = dto.getGroupId() != null ? studentGroupRepository
                .findById(dto.getGroupId())
                .orElseThrow(StudentGroupNotFoundException::new) : null;

        return studentRepository.save(
                Student.builder()
                        .id(dto.getId())
                        .user(details.getUser())
                        .surname(dto.getSurname())
                        .name(dto.getName())
                        .patronymic(dto.getPatronymic())
                        .phone(dto.getPhone())
                        .studentGroup(group)
                        .build()
        );
    }

    @Override
    public void deleteStudentById(Long id, AppUserDetails details) {
        throw new AccessException();
    }
}
