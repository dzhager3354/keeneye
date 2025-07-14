package ru.dzhager3354.keeneye.service.teachers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateTeacherDto;
import ru.dzhager3354.keeneye.dto.UpdateTeacherDto;
import ru.dzhager3354.keeneye.entity.Role;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.entity.User;
import ru.dzhager3354.keeneye.exceptions.TeacherNotFoundException;
import ru.dzhager3354.keeneye.exceptions.UserNotFoundException;
import ru.dzhager3354.keeneye.repository.TeacherRepository;
import ru.dzhager3354.keeneye.repository.UserRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceForAdmin implements ITeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Override
    public List<Teacher> getTeachers(AppUserDetails details) {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher createTeacher(CreateTeacherDto dto, AppUserDetails details) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(UserNotFoundException::new);
        user.setUserRole(Role.ROLE_TEACHER);
        user = userRepository.save(user);

        return teacherRepository.save(
                Teacher.builder()
                        .surname(dto.getSurname())
                        .name(dto.getName())
                        .patronymic(dto.getPatronymic())
                        .user(user)
                        .build()
        );
    }

    @Override
    public Teacher updateTeacher(UpdateTeacherDto dto, AppUserDetails details) {
        Teacher teacher = teacherRepository.findById(dto.getId()).orElseThrow(TeacherNotFoundException::new);

        teacher.setSurname(dto.getSurname());
        teacher.setName(dto.getName());
        teacher.setPatronymic(dto.getPatronymic());
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(Long id, AppUserDetails details) {
        teacherRepository.deleteById(id);
    }
}
