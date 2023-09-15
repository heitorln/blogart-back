package com.example.blogart.controller;

import com.example.blogart.domain.user.User;
import com.example.blogart.repositories.UserRepository;
import com.example.blogart.dtos.user.UserRequestDTO;
import com.example.blogart.dtos.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository repository;
    @GetMapping
    public List<UserResponseDTO> getAll(){

        return repository.findAll().stream().map(UserResponseDTO::new).toList();
    }

    @PostMapping
    public void saveNew(@RequestBody UserRequestDTO body){
        User newUser = new User(body);
        repository.save(newUser);
    }
}
