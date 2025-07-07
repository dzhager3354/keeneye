package ru.dzhager3354.keeneye.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.exceptions.UserNotFoundException;
import ru.dzhager3354.keeneye.repository.TeacherRepository;
import ru.dzhager3354.keeneye.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public List<StudentGroup> getAllGroups() {
        return userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        )
                .orElseThrow(UserNotFoundException::new)
                .getTeacher()
                .getStudentGroups();
    }
}
