package ru.dzhager3354.keeneye.service.students;

import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents(AppUserDetails details);

    Student createStudent(CreateStudentDto dto, AppUserDetails details);

    Student getStudentById(Long id, AppUserDetails details);

    Student updateStudent(UpdateStudentDto dto, AppUserDetails details);

    void deleteStudentById(Long id, AppUserDetails details);
}
