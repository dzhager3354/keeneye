package ru.dzhager3354.keeneye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhager3354.keeneye.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
