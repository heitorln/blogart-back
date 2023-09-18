package com.example.blogart.services;

import com.example.blogart.domain.comment.Comment;
import com.example.blogart.domain.post.Post;
import com.example.blogart.dtos.comment.CommentResponseDTO;
import com.example.blogart.dtos.post.PostResponseDTO;
import com.example.blogart.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository repository;
    @Autowired
    PostService postService;
    @Autowired
    TokenService tokenService;

    public CommentResponseDTO createComment(Comment comment, String token){
        if (tokenService.validateToken(token, comment.getUser().getUsername())) {
            // TODO timezone
            Timestamp currentTS = new Timestamp(System.currentTimeMillis());
            comment.setCreatedAt(currentTS);

            Comment createdComment = repository.save(comment);

            return new CommentResponseDTO(createdComment);
        } else {
            throw new AccessDeniedException("Invalid user");
        }
    }

    public List<CommentResponseDTO> getAllCommentsFromPost(Long postId){
        PostResponseDTO commentPost = postService.getPostById(postId);

        if (commentPost != null) {
            List<Comment> comments = repository.findByPostId(postId);
            return comments.stream().map(CommentResponseDTO::new).toList();
        }

        return null;
    }

    public CommentResponseDTO getCommentById(Long id){
        Optional<Comment> optionalComment = repository.findById(id);

        return optionalComment.map(CommentResponseDTO::new).orElse(null);
    }

    public void deleteComment(Long commentId, Long userId, String token){
        Optional<Comment> optionalComment = repository.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (comment.getUser().getId().equals(userId) && tokenService.validateToken(token, comment.getUser().getUsername())){
                repository.deleteById(commentId);
            } else {
                throw new AccessDeniedException("You are not authorized to delete this comment.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found with ID: " + commentId);
        }
    }
}
