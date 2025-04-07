package com.example.testsecurity.Controllers;

import com.example.testsecurity.Models.UserApp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.testsecurity.Service.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserAppService userAppService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserAppService userAppService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userAppService = userAppService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginForm() {
        System.out.println("Pokazuje formularz");
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserApp user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);



        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserApp());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserApp user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userAppService.saveUser(user);
        return "redirect:/auth/login?success";
    }


}

