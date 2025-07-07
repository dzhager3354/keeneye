package ru.dzhager3354.keeneye.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class StudentDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(AppUserDetails::new)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}
