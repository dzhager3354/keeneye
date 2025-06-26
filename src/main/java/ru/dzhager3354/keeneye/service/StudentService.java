package ru.dzhager3354.keeneye.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentDto;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.repository.StudentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentDto dto) {
        Student newStudent = Student.builder()
                .surname(dto.getSurname())
                .name(dto.getName())
                .patronymic(dto.getPatronymic())
                .phone(dto.getPhone())
                .build();
        return studentRepository.save(newStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student updateStudent(Student student) {
        if (student.getId() == null) {
            throw new RuntimeException();
        }
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        if (id == null) {
            throw new RuntimeException();
        }
        studentRepository.deleteById(id);
    }
}
