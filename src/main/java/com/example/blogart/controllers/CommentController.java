package com.example.blogart.controllers;

import com.example.blogart.domain.comment.Comment;
import com.example.blogart.domain.post.Post;
import com.example.blogart.domain.user.User;
import com.example.blogart.dtos.comment.CommentRequestDTO;
import com.example.blogart.dtos.comment.CommentResponseDTO;
import com.example.blogart.services.CommentService;
import com.example.blogart.services.PostService;
import com.example.blogart.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody @Valid CommentRequestDTO body,
                                                            @RequestHeader("Authorization") String token){
        User user = userService.findUserById(body.userId());
        Post post = postService.findPostById(body.postId());

        Comment newComment = new Comment(body.text(), post, user);

        CommentResponseDTO response = commentService.createComment(newComment, token);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Long commentId){
        CommentResponseDTO comment = commentService.getCommentById(commentId);

        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsFromPost(@RequestParam Long postId){
        List<CommentResponseDTO> commentsFromPost = commentService.getAllCommentsFromPost(postId);
        return new ResponseEntity<>(commentsFromPost, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                           @RequestParam Long userId,
                                           @RequestHeader("Authorization") String token) {
        commentService.deleteComment(commentId, userId, token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
