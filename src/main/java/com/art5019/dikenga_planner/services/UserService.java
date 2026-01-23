package com.art5019.dikenga_planner.services;

import com.art5019.dikenga_planner.dto.UserRegisterDTO;
import com.art5019.dikenga_planner.exceptions.DuplicatedUserException;
import com.art5019.dikenga_planner.model.User;
import com.art5019.dikenga_planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ur.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public User registerUser(UserRegisterDTO urdto) {
        String email = urdto.email();
        User u = new User(urdto.username(), email, urdto.password());
        if(ur.existsByEmail(email)) {
            throw new DuplicatedUserException("User already exists!");
        }
        return ur.save(u);
    }
}
