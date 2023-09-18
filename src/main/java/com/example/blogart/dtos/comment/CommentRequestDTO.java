package com.example.blogart.dtos.comment;

public record CommentRequestDTO(String text, Long userId, Long postId) {
}
