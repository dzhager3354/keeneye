package ru.dzhager3354.keeneye.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.*;
import ru.dzhager3354.keeneye.entity.Student;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.exceptions.StudentGroupNotFoundException;
import ru.dzhager3354.keeneye.exceptions.StudentNotFoundException;
import ru.dzhager3354.keeneye.exceptions.TeacherNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentGroupRepository;
import ru.dzhager3354.keeneye.repository.StudentRepository;
import ru.dzhager3354.keeneye.repository.TeacherRepository;
import ru.dzhager3354.keeneye.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    public Student createStudent(CreateStudentDto dto) {
        return studentRepository.save(
                Student.builder()
                        .user(userRepository.getReferenceById(dto.getUserId()))
                        .surname(dto.getSurname())
                        .name(dto.getName())
                        .patronymic(dto.getPatronymic())
                        .studentGroup(studentGroupRepository.getReferenceById(dto.getGroup()))
                        .phone(dto.getPhone())
                        .build()
        );
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Student updateStudent(UpdateStudentDto dto) {
        Student student = studentRepository.findById(dto.getId()).orElseThrow(StudentNotFoundException::new);

        student.setSurname(dto.getSurname());
        student.setName(dto.getName());
        student.setPatronymic(dto.getPatronymic());
        student.setPhone(dto.getPhone());
        student.setStudentGroup(studentGroupRepository.findById(dto.getGroupId()).get());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Teacher createTeacher(CreateTeacherDto dto) {
        return teacherRepository.save(
                Teacher.builder()
                        .surname(dto.getSurname())
                        .name(dto.getName())
                        .patronymic(dto.getPatronymic())
                        .user(userRepository.getReferenceById(dto.getUserId()))
                        .build()
        );
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(TeacherNotFoundException::new);
    }

    public Teacher updateTeacher(UpdateTeacherDto dto) {
        Teacher teacher = teacherRepository.findById(dto.getId())
                .orElseThrow(TeacherNotFoundException::new);

        teacher.setName(dto.getName());
        teacher.setSurname(dto.getSurname());
        teacher.setPatronymic(dto.getPatronymic());

        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public List<StudentGroup> getAllStudentGroup() {
        return studentGroupRepository.findAll();
    }

    public StudentGroup getStudentGroupById(Long id) {
        return studentGroupRepository.findById(id).orElseThrow(StudentGroupNotFoundException::new);
    }

    public StudentGroup createGroup(CreateGroupDto dto) {
        return studentGroupRepository.save(
                StudentGroup.builder()
                        .name(dto.getName())
                        .teacher(teacherRepository.getReferenceById(dto.getTeacherId()))
                        .build()
        );
    }

    public StudentGroup updateGroup() {

    }
}
