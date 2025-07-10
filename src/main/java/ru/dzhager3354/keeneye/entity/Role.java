package ru.dzhager3354.keeneye.entity;

public enum Role {
    ROLE_STUDENT,
    ROLE_ADMIN,
    ROLE_TEACHER;

    public String getName() {
        return name().substring(5);
    }
}
