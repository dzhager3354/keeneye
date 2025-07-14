package ru.dzhager3354.keeneye.service.teachers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateTeacherDto;
import ru.dzhager3354.keeneye.dto.UpdateTeacherDto;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.exceptions.AccessException;
import ru.dzhager3354.keeneye.repository.TeacherRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TeacherServiceForTeacher implements ITeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getTeachers(AppUserDetails details) {
        return List.of(details.getUser().getTeacher());
    }

    @Override
    public Teacher createTeacher(CreateTeacherDto dto, AppUserDetails details) {
        throw new AccessException();
    }

    @Override
    public Teacher updateTeacher(UpdateTeacherDto dto, AppUserDetails details) {
        Teacher teacher = details.getUser().getTeacher();
        if (!Objects.equals(teacher.getId(), dto.getId())) {
            throw new AccessException();
        }
        teacher.setSurname(dto.getSurname());
        teacher.setName(dto.getName());
        teacher.setPatronymic(dto.getPatronymic());
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(Long id, AppUserDetails details) {
        throw new AccessException();
    }
}
