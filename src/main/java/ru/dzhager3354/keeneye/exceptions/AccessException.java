package ru.dzhager3354.keeneye.exceptions;

import org.springframework.security.access.AccessDeniedException;

public class AccessException extends AccessDeniedException {
    public AccessException() {
        super("Denied");
    }
}
