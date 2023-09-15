package com.example.blogart.user;

import jakarta.persistence.*;

@Table(name = "user", schema = "public")
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String email;
}
