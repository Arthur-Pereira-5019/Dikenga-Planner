package com.art5019.dikenga_planner.services;

import com.art5019.dikenga_planner.dto.UserPresentationDTO;
import com.art5019.dikenga_planner.dto.UserRegisterDTO;
import com.art5019.dikenga_planner.exceptions.DuplicatedUserException;
import com.art5019.dikenga_planner.exceptions.UserNotFoundException;
import com.art5019.dikenga_planner.model.PasswordService;
import com.art5019.dikenga_planner.model.Project;
import com.art5019.dikenga_planner.model.User;
import com.art5019.dikenga_planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User updateUser(User u) {
        ps.validatePassword(u.getPassword());
        return ur.save(u);
    }

    public UserPresentationDTO presentUser(User u) {
        return new UserPresentationDTO(u.getName());
    }

    public User findById(Long id) {
        return ur.findById(id).orElseThrow(() -> new UserNotFoundException("Not found any user with the provided Id"));
    }

    public List<Project> findAllProjects(User u) {
        return u.getProjects();
    }

    public User findByUserDetails(UserDetails ud) {
        return (User) loadUserByUsername(ud.getUsername());
    }

}
