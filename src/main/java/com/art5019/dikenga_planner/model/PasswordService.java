package com.art5019.dikenga_planner.model;

import com.art5019.dikenga_planner.exceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private PasswordEncoder encoder;

    public String passwordRegex;

    public boolean validatePassword(String password) {
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
        return true;
    }

    public String encryptPassword(String password) {
        return encoder.encode(password);
    }
}
