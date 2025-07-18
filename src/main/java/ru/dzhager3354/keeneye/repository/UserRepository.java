package ru.dzhager3354.keeneye.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhager3354.keeneye.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
