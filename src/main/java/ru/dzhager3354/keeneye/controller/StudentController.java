package ru.dzhager3354.keeneye.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.service.StudentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/students")
@Tag(
        name = "Student controller",
        description = "Создание, редактирование и удаление студентов"
)
public class StudentController {
    private StudentService studentService;

    @GetMapping("/all")
    @Operation(
            summary = "Получение всех одногруппников"
    )
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение пользователя по его id"
    )
    public Student getStudent(@NotNull @PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/create")
    @Operation(
            summary = "Создание студента на основе дто"
    )
    public Student create(@Valid @RequestBody CreateStudentDto dto) {
        return studentService.createStudent(dto);
    }

    @PutMapping("/update")
    @Operation(
            summary = "Обновление данных пользователя"
    )
    public Student update(@Valid @RequestBody UpdateStudentDto student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление пользователя по его id"
    )
    public void delete(@NotNull @PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
    }
}
