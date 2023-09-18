package com.example.blogart.dtos.post;

import com.example.blogart.domain.post.Post;

import java.util.Date;

public record PostResponseDTO(Long id, String title, String content, Long userId, Date createdAt) {
    public PostResponseDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId(), post.getCreatedAt());
    }
}
