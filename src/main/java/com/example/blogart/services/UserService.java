package com.example.blogart.services;

import com.example.blogart.domain.user.User;
import com.example.blogart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User findUserById(Long id){
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElse(null);
    }

    public UserDetails findUserByUsername(String username){
        return repository.findUserByUsername(username);
    }

    public void createUser(User user){
        repository.save(user);
    }
}
