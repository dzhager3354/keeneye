package ru.dzhager3354.keeneye.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.dzhager3354.keeneye.dto.CreateTeacherDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateTeacherDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.security.AppUserDetails;
import ru.dzhager3354.keeneye.service.teachers.TeacherService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService service;

    @GetMapping
    public List<Teacher> getTeachers(@AuthenticationPrincipal AppUserDetails userDetails) {
        return service.getTeachers(userDetails);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody CreateTeacherDto dto, @AuthenticationPrincipal AppUserDetails userDetails) {
        return service.createTeacher(dto, userDetails);
    }

    @PutMapping
    public Teacher updateTeacher(@RequestBody UpdateTeacherDto dto, @AuthenticationPrincipal AppUserDetails userDetails) {
        return service.updateTeacher(dto, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") Long id, @AuthenticationPrincipal AppUserDetails userDetails) {
        service.deleteTeacherById(id, userDetails);
    }
}
