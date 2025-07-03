package ru.dzhager3354.keeneye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhager3354.keeneye.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);
}
