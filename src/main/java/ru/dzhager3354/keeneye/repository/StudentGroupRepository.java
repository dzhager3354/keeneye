package ru.dzhager3354.keeneye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhager3354.keeneye.entity.StudentGroup;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
}
