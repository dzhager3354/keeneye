package ru.dzhager3354.keeneye.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.dto.CreateTeacherDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.service.AdminService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/student")
    public Student createStudent(CreateStudentDto dto) {
        return adminService.createStudent(dto);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return adminService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id") Long id) {
        return adminService.getStudentById(id);
    }

    @PutMapping("/student")
    public Student updateStudent(@RequestBody UpdateStudentDto dto) {
        return adminService.updateStudent(dto);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        adminService.deleteStudent(id);
    }

    @PutMapping("/teacher")
    public Teacher createTeacher(@RequestBody CreateTeacherDto dto) {
        return adminService.createTeacher(dto);
    }
}
