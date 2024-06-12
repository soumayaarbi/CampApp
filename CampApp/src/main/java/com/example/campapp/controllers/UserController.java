package com.example.campapp.controllers;

import com.example.campapp.entities.Utilisateur;
import com.example.campapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    UserService userService;
    @GetMapping("/all")
    public List<Utilisateur> getAllUser() {
        return userService.getAllUser();
    }
}
