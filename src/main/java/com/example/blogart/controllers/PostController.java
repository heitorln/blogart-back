package com.example.blogart.controllers;

import com.example.blogart.domain.post.Post;
import com.example.blogart.domain.user.User;
import com.example.blogart.dtos.post.PostRequestDTO;
import com.example.blogart.dtos.post.PostResponseDTO;
import com.example.blogart.services.PostService;
import com.example.blogart.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody @Valid PostRequestDTO body){
        User user = userService.findUserById(body.userId());

        Post newPost = new Post(body.title(), body.content(), user);

        PostResponseDTO response = postService.createPost(newPost);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(){
        List<PostResponseDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId){
        PostResponseDTO post = postService.getPostById(postId);

        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId,
                                           @RequestParam Long userId,
                                           @RequestHeader("Authorization") String token) {
        postService.deletePost(postId, userId, token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
