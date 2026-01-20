package com.art5019.dikenga_planner.model;

import com.art5019.dikenga_planner.exceptions.InvalidEmailException;
import com.art5019.dikenga_planner.exceptions.InvalidName;
import com.art5019.dikenga_planner.exceptions.InvalidPasswordException;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @OneToMany
    private List<Project> projects;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username == null || username.isBlank()) {
            throw new InvalidName("Empty username!");
        }
        if(username.length() > 255) {
            throw new InvalidName("Way too long username!");
        }
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.isBlank()) {
            throw new InvalidName("Empty email!");
        }
        if(email.length() > 254) {
            throw new InvalidName("Way too long email!");
        }
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidEmailException("Invalid Email format!");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null || password.isBlank()) {
            throw new InvalidPasswordException("Empty password!");
        }
        if(email.length() > 255) {
            throw new InvalidPasswordException("Way too long password!");
        }
        if(password.length() < 8) {
            throw new InvalidPasswordException("Way too small password!");
        }
        if(!email.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%.^&*-]).{8,}$")) {
            throw new InvalidPasswordException("Invalid password format!");
        }
        this.password = password;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProjects(Project... projects) {
        this.projects.addAll(List.of(projects));
    }
}
