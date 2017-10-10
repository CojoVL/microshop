package ro.microservices.auth.services;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.microservices.auth.entities.UserDetailsEntity;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @Override
    public UserDetailsEntity loadUserByUsername(final String username) throws UsernameNotFoundException {
        return new UserDetailsEntity("reader",
                passwordEncoder.encode("12345678"),
                Arrays.asList(new SimpleGrantedAuthority("READ")),
                "Lucian Neghina");
    }
}
