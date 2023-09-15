package com.example.blogart.domain.user;

import com.example.blogart.dtos.user.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "blogUser")
@Entity(name = "blogUser")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String email;

    public User(UserRequestDTO body){
        this.username = body.username();
        this.password = body.password();
        this.name = body.name();
        this.email = body.email();
    }
}
