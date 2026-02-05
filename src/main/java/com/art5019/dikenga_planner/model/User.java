package com.art5019.dikenga_planner.model;

import com.art5019.dikenga_planner.exceptions.InvalidEmailException;
import com.art5019.dikenga_planner.exceptions.InvalidName;
import com.art5019.dikenga_planner.exceptions.InvalidPasswordException;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @OneToMany
    private List<Project> projects;

    public User() {
    }

    public User(String name, String email, String password) {
        validateName(name);
        validateMail(email);
        validatePassword(password);
        password = (new BCryptPasswordEncoder().encode(password));
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    private void validateName(String name) {
        if(name == null || name.isBlank()) {
            throw new InvalidName("Empty username!");
        }
        if(name.length() > 255) {
            throw new InvalidName("Way too long username!");
        }
    }

    private void validateMail(String email) {
        if(email == null || email.isBlank()) {
            throw new InvalidName("Empty email!");
        }
        if(email.length() > 254) {
            throw new InvalidName("Way too long email!");
        }
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidEmailException("Invalid Email format!");
        }
    }

    private void validatePassword(String password) {
        if(password == null || password.isBlank()) {
            throw new InvalidPasswordException("Empty password!");
        }
        if(password.length() > 255) {
            throw new InvalidPasswordException("Way too long password!");
        }
        if(password.length() < 8) {
            throw new InvalidPasswordException("Way too small password!");
        }
        if(!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%.^&*-]).{8,}$")) {
            throw new InvalidPasswordException("Invalid password format!");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateMail(email);
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProjects(Project... projects) {
        this.projects.addAll(List.of(projects));
    }
    
}
