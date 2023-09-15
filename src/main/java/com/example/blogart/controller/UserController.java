package com.example.blogart.controller;

import com.example.blogart.user.User;
import com.example.blogart.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository repository;
    @GetMapping
    public List<User> getAll(){

        return repository.findAll();
    }
}
