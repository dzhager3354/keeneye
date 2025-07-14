package ru.dzhager3354.keeneye.service.teachers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateTeacherDto;
import ru.dzhager3354.keeneye.dto.UpdateTeacherDto;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.exceptions.AccessException;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceForStudent implements ITeacherService {
    @Override
    public List<Teacher> getTeachers(AppUserDetails details) {
        return List.of(details.getUser().getStudent().getStudentGroup().getTeacher());
    }

    @Override
    public Teacher createTeacher(CreateTeacherDto dto, AppUserDetails details) {
        throw new AccessException();
    }

    @Override
    public Teacher updateTeacher(UpdateTeacherDto dto, AppUserDetails details) {
        throw new AccessException();
    }

    @Override
    public void deleteTeacherById(Long id, AppUserDetails details) {
        throw new AccessException();
    }
}
