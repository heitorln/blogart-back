package com.example.blogart.dtos.user;

import com.example.blogart.domain.user.User;

public record UserResponseDTO(Long id, String username, String password, String name, String email) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getUsername(),user.getPassword(), user.getName(), user.getEmail());
    }
}
