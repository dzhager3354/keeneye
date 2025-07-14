package ru.dzhager3354.keeneye.service.students;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.entity.User;
import ru.dzhager3354.keeneye.exceptions.AccessException;
import ru.dzhager3354.keeneye.exceptions.StudentGroupNotFoundException;
import ru.dzhager3354.keeneye.exceptions.StudentNotFoundException;
import ru.dzhager3354.keeneye.exceptions.UserNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentGroupRepository;
import ru.dzhager3354.keeneye.repository.StudentRepository;
import ru.dzhager3354.keeneye.repository.UserRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentServiceForAdmin implements IStudentService {
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final UserRepository userRepository;

    @Override
    public List<Student> getAllStudents(AppUserDetails details) {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(CreateStudentDto dto, AppUserDetails details) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(UserNotFoundException::new);
        StudentGroup group = dto.getGroup() != null ? studentGroupRepository
                .findById(dto.getGroup())
                .orElse(null) : null;

        return studentRepository.save(
                Student.builder()
                        .user(user)
                        .surname(dto.getSurname())
                        .name(dto.getName())
                        .patronymic(dto.getPatronymic())
                        .studentGroup(group)
                        .phone(dto.getPhone())
                        .build()
        );
    }

    @Override
    public Student getStudentById(Long id, AppUserDetails details) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Student updateStudent(UpdateStudentDto dto, AppUserDetails details) {
        if (!studentRepository.existsById(dto.getId())) {
            throw new UserNotFoundException();
        }

        Student student = studentRepository.findById(dto.getId()).get();
        StudentGroup studentGroup = studentGroupRepository.findById(dto.getGroupId()).orElseThrow(StudentGroupNotFoundException::new);

        student.setSurname(dto.getSurname());
        student.setName(dto.getName());
        student.setPatronymic(dto.getPatronymic());
        student.setStudentGroup(studentGroup);
        student.setPhone(dto.getPhone());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id, AppUserDetails details) {

    }
}
