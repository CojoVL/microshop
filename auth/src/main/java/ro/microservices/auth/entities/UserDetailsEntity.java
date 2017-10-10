package ro.microservices.auth.entities;

import java.util.List;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Data
public class UserDetailsEntity extends User {
    private String fullname;

    public UserDetailsEntity(final String username, final String password,
                             final List<SimpleGrantedAuthority> authorities, final String fullname) {
        super(username, password, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, authorities);
        this.fullname = fullname;
    }
}
