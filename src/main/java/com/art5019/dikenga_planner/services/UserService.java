package com.art5019.dikenga_planner.services;

import com.art5019.dikenga_planner.dto.UserRegisterDTO;
import com.art5019.dikenga_planner.exceptions.DuplicatedUserException;
import com.art5019.dikenga_planner.model.User;
import com.art5019.dikenga_planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository ur;

    public User registerUser(UserRegisterDTO urdto) {
        String email = urdto.email();
        User u = new User(urdto.username(), email, urdto.password());
        if(ur.existsByEmail(email)) {
            throw new DuplicatedUserException("User already exists!");
        }
        return ur.save(u);
    }
}
