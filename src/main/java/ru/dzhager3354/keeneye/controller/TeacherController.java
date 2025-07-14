package ru.dzhager3354.keeneye.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.service.teachers.TeacherService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/groups")
    public List<StudentGroup> getGroups() {
        return teacherService.getAllGroups();
    }

    @PutMapping("/student")
    public Student updateStudent(UpdateStudentDto dto) {
        return teacherService.updateStudent(dto);
    }
}
