package com.art5019.dikenga_planner.services;

import com.art5019.dikenga_planner.dto.UserPresentationDTO;
import com.art5019.dikenga_planner.dto.UserRegisterDTO;
import com.art5019.dikenga_planner.exceptions.DuplicatedUserException;
import com.art5019.dikenga_planner.exceptions.UserNotFoundException;
import com.art5019.dikenga_planner.model.PasswordService;
import com.art5019.dikenga_planner.model.User;
import com.art5019.dikenga_planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository ur;

    @Autowired
    PasswordService ps;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return ur.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public User registerUser(UserRegisterDTO urdto) {
        String email = urdto.email();
        String password = urdto.password();
        ps.validatePassword(password);
        User u = new User(urdto.username(), email, ps.encryptPassword(password));
        if(ur.existsByEmail(email)) {
            throw new DuplicatedUserException("User already exists!");
        }
        return ur.save(u);
    }

    public UserPresentationDTO presentUser(String username) {
        User u = (User) loadUserByUsername(username);
        return new UserPresentationDTO(u.getName());
    }
}
