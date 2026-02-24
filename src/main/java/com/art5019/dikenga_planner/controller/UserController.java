package com.art5019.dikenga_planner.controller;

import com.art5019.dikenga_planner.dto.LoginRequestDTO;
import com.art5019.dikenga_planner.dto.UserPresentationDTO;
import com.art5019.dikenga_planner.dto.UserRegisterDTO;
import com.art5019.dikenga_planner.model.User;
import com.art5019.dikenga_planner.repository.UserRepository;
import com.art5019.dikenga_planner.services.TokenService;
import com.art5019.dikenga_planner.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.header.writers.PermissionsPolicyHeaderWriter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService us;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService ts;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO pl, HttpServletResponse response) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(pl.email(), pl.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = ts.generateToken((User) auth.getPrincipal());

        Cookie cookie = new Cookie("login", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(pl.remember() ? 3628800 : 86400);
        response.addCookie(cookie);

        return ResponseEntity.ok("Loged with success!");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO u, HttpServletResponse response) {
        String oldPassword = u.password();
        us.registerUser(u);

        var usernamePassword = new UsernamePasswordAuthenticationToken(u.email(), oldPassword);
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println("3");
        var token = ts.generateToken((User) auth.getPrincipal());
        System.out.println("4");


        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(1209600);
        response.addCookie(cookie);

        return ResponseEntity.ok("Registered with success!");
    }

    @GetMapping("/present")
    public UserPresentationDTO userPresentationDTO(@AuthenticationPrincipal UserDetails ud) {
        return us.presentUser(ud.getUsername());
    }
}
