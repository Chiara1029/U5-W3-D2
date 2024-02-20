package com.chiarapuleio.devicesmanagement.entities;

import com.chiarapuleio.devicesmanagement.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="employees")
@JsonIgnoreProperties({"password", "credentialsNonExpired", "accountNonExpired", "authorities", "username", "accountNonLocked", "enabled"})
public class Employee  implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String avatarUrl;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Employee(String username, String name, String surname, String email,  String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
