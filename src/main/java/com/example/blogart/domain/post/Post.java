package com.example.blogart.domain.post;

import com.example.blogart.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Table(name = "post")
@Entity(name = "Post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public Post(String title, String content, User user) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}
