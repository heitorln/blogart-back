package com.example.blogart.domain.comment;

import com.example.blogart.domain.post.Post;
import com.example.blogart.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "comment")
@Entity(name = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @NotNull
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public Comment(String text, Post post, User user) {
        this.user = user;
        this.post = post;
        this.text = text;
    }
}
