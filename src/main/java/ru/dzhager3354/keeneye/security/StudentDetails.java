package ru.dzhager3354.keeneye.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dzhager3354.keeneye.entity.Student;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class StudentDetails implements UserDetails {
    private final Student student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(student.getUserRole().name()));
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        return student.getUsername();
    }
}
