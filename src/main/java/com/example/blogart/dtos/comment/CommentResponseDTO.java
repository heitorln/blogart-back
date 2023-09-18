package com.example.blogart.dtos.comment;

import com.example.blogart.domain.comment.Comment;
import com.example.blogart.domain.post.Post;

import java.util.Date;

public record CommentResponseDTO(Long id, String text, Long userId, Long postId, Date createdAt) {
    public CommentResponseDTO(Comment comment) {
        this(comment.getId(), comment.getText(), comment.getUser().getId(), comment.getPost().getId(), comment.getCreatedAt());
    }
}
