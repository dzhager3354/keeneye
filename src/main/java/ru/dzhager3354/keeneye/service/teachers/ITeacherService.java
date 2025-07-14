package ru.dzhager3354.keeneye.service.teachers;

import ru.dzhager3354.keeneye.dto.CreateTeacherDto;
import ru.dzhager3354.keeneye.dto.UpdateTeacherDto;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;

public interface ITeacherService {
    List<Teacher> getTeachers(AppUserDetails details);

    Teacher createTeacher(CreateTeacherDto dto, AppUserDetails details);

    Teacher updateTeacher(UpdateTeacherDto dto, AppUserDetails details);

    void deleteTeacherById(Long id, AppUserDetails details);
}
